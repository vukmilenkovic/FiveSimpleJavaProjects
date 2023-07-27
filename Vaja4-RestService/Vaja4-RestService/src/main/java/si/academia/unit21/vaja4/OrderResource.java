package si.academia.unit21.vaja4;


import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


@Path("/orders")
@Singleton
public class OrderResource {
    private Hashtable<Integer, Order> orders = new Hashtable<>();
    private Hashtable<Integer, Hashtable<Integer, Item>> items = new Hashtable<>();

    public OrderResource() {
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Order getOrderById(@PathParam("id") int id) { return orders.get(id); }

    @GET
    @Produces("application/json")
    public List<Order> getOrders() { return new ArrayList<Order>(orders.values()); }


    @POST
    @Consumes("application/json")
    public Response createOrder(Order order, @Context UriInfo uriInfo) {
        int orderId = orders.size() + 1;
        order.setId(orderId);
        orders.put(orderId, order);
        items.put(orderId, new Hashtable<>());
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(orderId));
        return Response.created(uriBuilder.build()).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes("application/json")
    public void updateOrder(@PathParam("id") int id, Order order) {
        orders.remove(id);
        orders.put(id, order);
        if (!items.containsKey(id)) { items.put(id, new Hashtable<>()); }
    }

    @Path("/{id}")
    @DELETE
    public void deleteOrder(@PathParam("id") int id) {
        orders.remove(id);
        items.remove(id);
    }

    @Path("/{orderId}/items/{id}")
    @GET
    @Produces("application/json")
    public Item getItemById(@PathParam("orderId") int orderId, @PathParam("id") int id) {
        if (!items.containsKey(orderId)) { return null; }
        return items.get(orderId).get(id);
    }

    @Path("/{orderId}/items")
    @GET
    @Produces("application/json")
    public List<Item> getItems(@PathParam("orderId") int orderId) {
        if (!items.containsKey(orderId)) { return null; }
        return new ArrayList<Item>(items.get(orderId).values());
    }

    @Path("/{orderId}/items")
    @POST
    @Consumes("application/json")
    public Response createItem(@PathParam("orderId") int orderId, Item item, @Context UriInfo uriInfo) {
        if (!items.containsKey(orderId)) { return Response.status(Response.Status.NOT_FOUND).build(); }
        int itemId = items.get(orderId).size() + 1;
        item.setId(itemId);
        items.get(orderId).put(itemId, item);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(itemId));
        return Response.created(uriBuilder.build()).build();
    }

    @Path("{orderId}/items/{id}")
    @PUT
    @Consumes("application/json")
    public Response updateItem(@PathParam("orderId") int orderId, @PathParam("id") int id, Item item) {
        if (!items.containsKey(orderId)) { return Response.status(Response.Status.NOT_FOUND).build(); }
        items.get(orderId).remove(id);
        items.get(orderId).put(id, item);
        return Response.noContent().build();

    }

    @Path("{orderId}/items/{id}")
    @DELETE
    public Response deleteItem(@PathParam("orderId") int orderId, @PathParam("id") int id) {
        if (!items.containsKey(orderId)) { return Response.status(Response.Status.NOT_FOUND).build(); }
        items.get(orderId).remove(id);
        return Response.noContent().build();
    }

}

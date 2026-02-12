package api.endpoints;

public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	// user module
	public static String post_Create_user=base_url+"/user";
	public static String get_user= base_url +"/user/{username}";
	public static String update_user= base_url +"/user/{username}";
	public static String delete_user=base_url +"/user/{username}";
	
	
	//store module
	
	public static String post_order=base_url+"/store/order";
	public static String get_order_by_id =base_url+"/store/order/{orderId}";
	public static String delete_order_by_id =base_url +"store/order/{orderId}";
	public static String get_inventory =base_url +"/store/inventory";

	//pet module 

} 

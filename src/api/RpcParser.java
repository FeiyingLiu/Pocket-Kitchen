package api;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A utility class to handle rpc related parsing logics. turn request to JSONObeject
 */
public class RpcParser {
	public static JSONObject parseInput(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
			reader.close();
			return new JSONObject(jb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// two write ways, create those two method for users to write in date
	public static void writeOutput(HttpServletResponse response, JSONObject obj) {
		try {			
			response.setContentType("application/json");
			// control authority
			response.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(obj);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void writeOutput(HttpServletResponse response, JSONArray array) {
		try {			
			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(array);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static Boolean sessionValid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if ( session.getAttribute("user") == null) return false;
		String user = (String) session.getAttribute("user");
		String user_in_url = request.getParameter("user_id");
		if (user_in_url!=null && !user_in_url.equals(user)){
			return false;
		}
		return true;
	}
}

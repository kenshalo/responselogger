package squareup.responselogger;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/location/*")
public class LocationServlet extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int code = new Random().nextInt(4);
		if (code == 0) {
			resp.setStatus(401);
		} else if (code == 1) {
			resp.setStatus(403);
		} else if (code == 2) {
			resp.setStatus(404);
		} else if (code == 3) {
			resp.setStatus(405);
		}
	}

	@Override
	protected void doPut(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		super.doPut(arg0, arg1);
	}

}

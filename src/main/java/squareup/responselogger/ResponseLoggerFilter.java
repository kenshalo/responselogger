package squareup.responselogger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ResponseLoggerFilter implements Filter {

	private FilterConfig config = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.config.getServletContext().setAttribute("200",
				new ResponseCollector());
		this.config.getServletContext().setAttribute("300",
				new ResponseCollector());
		this.config.getServletContext().setAttribute("400",
				new ResponseCollector());
		this.config.getServletContext().setAttribute("500",
				new ResponseCollector());
	}

	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, res);

		int code = ((HttpServletResponse) res).getStatus();
		ServletContext ctx = this.config.getServletContext();
		ResponseCollector c = null;
		if (code < 300) {
			c = (ResponseCollector) ctx.getAttribute("200");
		} else if (code < 400) {
			c = (ResponseCollector) ctx.getAttribute("300");
		} else if (code < 500) {
			c = (ResponseCollector) ctx.getAttribute("400");
		} else if (code < 600) {
			c = (ResponseCollector) ctx.getAttribute("500");
		}

		c.processValue(code);
		prettyPrint();
	}

	private void prettyPrint() {
		StringWriter sw = new StringWriter();
		PrintWriter writer = new PrintWriter(sw);
		ServletContext ctx = this.config.getServletContext();
		ResponseCollector c200 = (ResponseCollector) ctx.getAttribute("200");
		ResponseCollector c300 = (ResponseCollector) ctx.getAttribute("300");
		ResponseCollector c400 = (ResponseCollector) ctx.getAttribute("400");
		ResponseCollector c500 = (ResponseCollector) ctx.getAttribute("500");

		writer.println("Response codes 200s: (average, variance) -> ("
				+ c200.getAve() + "," + c200.getVar() + ")");
		writer.println("Response codes 300s: (average, variance) -> ("
				+ c300.getAve() + "," + c300.getVar() + ")");
		writer.println("Response codes 400s: (average, variance) -> ("
				+ c400.getAve() + "," + c400.getVar() + ")");
		writer.println("Response codes 500s: (average, variance) -> ("
				+ c500.getAve() + "," + c500.getVar() + ")");

		System.out.println(sw.getBuffer().toString());
	}

}

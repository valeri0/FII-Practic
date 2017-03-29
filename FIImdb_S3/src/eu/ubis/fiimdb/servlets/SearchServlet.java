package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SearchServlet() {
        super(); 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchedValue=request.getParameter("searchedValue");
		String searchType=request.getParameter("searchType");
		
		/* 
		 * 2. create an object of type MovieBean and call search method
		 */
		
		MovieBean bean = new MovieBean();
		
		bean.search(searchType, searchedValue.toLowerCase());
		
		/*
		 * 3. set the bean as an attribute to the request 
		 * make sure that it is the name from movies.jsp (see tag <jsp:useBean>)
		 */
		
		request.setAttribute("movieBean", bean);
		

		/*
		 * 4. set the type of the search that was made
		 */
		
		request.setAttribute("searchType", searchType);

		/*
		 * 5. redirect to movies.jsp
		 */
		getServletContext().getRequestDispatcher("/movies.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}

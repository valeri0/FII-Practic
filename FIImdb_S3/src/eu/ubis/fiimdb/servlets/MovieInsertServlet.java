package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;

@WebServlet("/MovieInsert")
public class MovieInsertServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// in acest servlet, trebuie sa luam datele introduse de user (disponibile in HttpServletRequest) si sa ne cream un obiect de tip Movie 
		// deci: 
		
		// 1. creati un obiect de tip Movie
		
		// 2. setati toate valorile obiectului 
		// exemplu:
		// movie.setName(req.getParameter("name"));
		
		// 3. pentru ID-urile genurilor filmului veti primi un array de String 
		// care trebuie transformat intr-un array de int 
		String[] genreIdStrings = req.getParameterValues("genres");
		int[] movieGenreIds = new int[genreIdStrings.length];
		for (int i = 0; i < genreIdStrings.length; i++) {
			movieGenreIds[i] = Integer.parseInt(genreIdStrings[i]);
		}

		// 4. creati un obiect MovieBean
		
		// 5. apelati metoda insertMovie din clasa MovieBean
		// hint: cautati-o in clasa MovieBean 
		// primeste ca parametri obiectul Movie si lista de ID-uri ale genurilor 
		// si apeleaza functia insertMovie din MovieService

		// 6. trimiteti utilizatorul la pagina principala
		resp.sendRedirect("movies");
	}
}

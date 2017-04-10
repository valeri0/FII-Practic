package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.db.dao.MovieDao;
import eu.ubis.fiimdb.model.Movie;

/**
 * Servlet implementation class UpdateMovieServlet
 */
@WebServlet("/UpdateMovie")
public class UpdateMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		MovieBean bean = new MovieBean();
		
		
		bean.setMovieDetail(id);
		
		request.setAttribute("movieBean", bean);
		
		request.getRequestDispatcher("/movie-update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Movie aMovie = new Movie();
		
		// 2. setati toate valorile obiectului 
		// exemplu:
		aMovie.setId(Integer.parseInt(req.getParameter("id")));
		aMovie.setName(req.getParameter("name"));
		aMovie.setPoster(req.getParameter("poster"));
		aMovie.setRating(Double.parseDouble(req.getParameter("rating")));
		aMovie.setLength(Integer.parseInt(req.getParameter("length")));
		aMovie.setCasting(req.getParameter("casting"));
		
		aMovie.setDescription(req.getParameter("description"));
		aMovie.setWriter(req.getParameter("writer"));
		
		// 3. pentru ID-urile genurilor filmului veti primi un array de String 
		// care trebuie transformat intr-un array de int 
		
		String[] genreIdStrings = req.getParameterValues("genres");
		int[] movieGenreIds = new int[genreIdStrings.length];
		for (int i = 0; i < genreIdStrings.length; i++) {
			movieGenreIds[i] = Integer.parseInt(genreIdStrings[i]);
		}
		
		String[] directorIdStrings = req.getParameterValues("directors");
		int[] movieDirectorIds = new int[directorIdStrings.length];
		for(int i=0;i<directorIdStrings.length;i++){
			movieDirectorIds[i]=Integer.parseInt(directorIdStrings[i]);
		}
	

		// 4. creati un obiect MovieBean
		
		MovieBean bean = new MovieBean();
		
		// 5. apelati metoda insertMovie din clasa MovieBean
		// hint: cautati-o in clasa MovieBean 
		// primeste ca parametri obiectul Movie si lista de ID-uri ale genurilor 
		// si apeleaza functia insertMovie din MovieService

		bean.updateMovie(aMovie, movieGenreIds,movieDirectorIds);
		
		resp.sendRedirect("movies");
		
		
		
		
	}

}

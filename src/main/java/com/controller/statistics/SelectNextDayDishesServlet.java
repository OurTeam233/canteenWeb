
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(name = "SelectNextDayDishesServlet", value = "/SelectNextDayDishesServlet")
public class SelectNextDayDishesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //初始化
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/json;charset=utf-8");
      PrintWriter out = response.getWriter();

      //处理


      //输出
      out.print("");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
    }

}
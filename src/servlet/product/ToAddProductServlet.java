package servlet.product;

import entity.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/toAddProductServlet")
public class ToAddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //实例化service层中CategoryService对象
        CategoryService service = new CategoryServiceImpl();
        //调用service层中findCategoryListByname方法
        List<Category> f = service.findCategoryListByName("father");
        List<Category> c = service.findCategoryListByName("child");
        //存值
        request.setAttribute("flist", f);
        request.setAttribute("clist", c);
        //请求重定向到admin_productadd.jsp页面
        request.getRequestDispatcher("admin_productadd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

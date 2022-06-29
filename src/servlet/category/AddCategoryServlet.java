package servlet.category;

import entity.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String a = request.getParameter("parentId");
        String b = request.getParameter("categoryName");

        System.out.println("parentId"+a);
        System.out.println("categoryName"+b);

        //创建商品分类服务层对象 查询该分类后传到前台
        //实例化对象
        Category category = new Category();
        category.setCategory_parentid(Integer.valueOf(a));
        category.setCategory_name(b);
        //实例化service层中CategoryService对象
        CategoryService service = new CategoryServiceImpl();
        //调用addCategory方法
        service.addCategory(category);

        PrintWriter out = response.getWriter();
        out.write("<script>");
        out.write("alert('插入分类成功！');");
        out.write("location.href='/HOMEECMS/categoryListServlet'");
        out.write("</script>");
        out.close();

//        request.getRequestDispatcher("").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

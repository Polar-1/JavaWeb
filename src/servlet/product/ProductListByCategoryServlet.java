package servlet.product;

import entity.Category;
import entity.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productListByCategoryServlet")
public class ProductListByCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品父类参数
        String parentId = request.getParameter("parentId");
        //分隔字符串,将字符串转化为数组
        String[] id = parentId.split("-");
        //转为Integer包装类型
        int productFid = Integer.valueOf(id[0]);//f
        int productCid = Integer.valueOf(id[1]);//c

        System.out.println(productCid);
        //实例化service层中ProductService对象
        ProductService service = new ProductServiceImpl();
        //调用service层中findProductByCategoryCid方法
        List<Product> plist = service.findProductByCategoryCid(productCid);

        request.setAttribute("plist", plist);
        //实例化service层中CategoryService对象
        CategoryService service2 = new CategoryServiceImpl();
        //调用service层中findCategoryListByname方法
        List<Category> f = service2.findCategoryListByName("father");
        List<Category> c = service2.findCategoryListByName("child");
        //存值
        request.setAttribute("flist", f);
        request.setAttribute("clist", c);
        //请求重定向到admin_product.jsp页面
        request.getRequestDispatcher("admin_product.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

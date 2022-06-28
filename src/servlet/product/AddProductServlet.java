package servlet.product;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import entity.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addProductServlet")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //中文乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        //文件上传
        String fileName = "";
        //创建SmartUpload对象
        SmartUpload su= new SmartUpload();
        //初始化SmartUpLoad对象
        su.initialize(this.getServletConfig(), request, response);
        try {
            //允许上传文件类型
            su.setAllowedFilesList("jpg,gif,jpeg,png");
            //上传文件
            su.upload();
            //获取当前系统时间
            String id = String.valueOf(System.currentTimeMillis());
            //获取文件
            Files uploadFiles = su.getFiles();
            //获取文件名称（包括文件扩展名）
            fileName = uploadFiles.getFile(0).getFileExt();
            //文件命名（避免重复 使用id.文件名）
            fileName = id+"."+fileName;
            //获取文件路径
            File f = new File(this.getServletContext().getRealPath("/upload/product"));
            if (!f.exists() && !f.isDirectory()) {
                f.mkdir();
            }
            //保存文件
            uploadFiles.getFile(0).saveAs("/upload/product/"+ fileName);

        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

        //获取商品名称、商品详情、商品价格、商品库存量、商品父类参数值
        String productName = su.getRequest().getParameter("productName");
        String productInfo = su.getRequest().getParameter("productInfo");
        int productPrice = Integer.valueOf(su.getRequest().getParameter("productPrice"));
        int  productStock = Integer.valueOf(su.getRequest().getParameter("productStock"));
        String productFatherChildid = su.getRequest().getParameter("parentId");
        //分隔字符串,将字符串转化为数组
        String[] ids = productFatherChildid.split("-");
        //转为Integer包装类型
        int productFid = Integer.valueOf(ids[0]);//f
        int productCid = Integer.valueOf(ids[1]);//c
        //实例化对象
        Product p = new Product(productName,productInfo,productPrice,productStock,productFid,productCid,fileName,new Date());
        //实例化service层中ProductService对象
        ProductService service = new ProductServiceImpl();
        //调用service层中addProduct方法
        service.addProduct(p);

        //前端响应
        PrintWriter out = response.getWriter();
        //弹出窗口
        out.write("<script>");
        out.write("alert('增加成功！');");
        out.write("location.href='/HOMEECMS/productListServlet'");
        out.write("</script>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.*;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)

public class UploadFileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        InputStream inputStream = null;

        String title = (request.getParameter("title"));
        Part filePart = request.getPart("file_uploaded");

        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            inputStream = filePart.getInputStream();
        }

        try {
            Insert_Csv_File Objresult = new Insert_Csv_File(); // ค่าที่ return กลับมาจากการ insert DB
            String success = Objresult.insert_csv(title, inputStream, filePart);

            if (success == "1") {
                /// ถ้า success แสดงอะไรบางอย่างออกมา แล้วแต่จะเขียน
                RequestDispatcher rs = request.getRequestDispatcher("upload_form.jsp");
                rs.include(request, response);
            } else {
                /// ถ้าไม่ success แสดงอะไรบางอย่างออกมา แล้วแต่จะเขียน อาจจะไปแสดงผลที่ไฟล์ error ก็ได้
                RequestDispatcher rs = request.getRequestDispatcher("upload_form.jsp");
                rs.include(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

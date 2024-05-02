
package Gui;

import Main.Run;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



public class GenerateReportAdmin extends JFrame implements ActionListener{
    
    public JButton total, gender,age,back;
    public static int total_admin = 0, total_lec =0 , total_stu =0;
    private static DecimalFormat df = new DecimalFormat("0.00");
    public String filename = "apu.png";
    public GenerateReportAdmin(){
    
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        total = new JButton("Total Number of Entities");
        total.addActionListener(this);
        add(total);
        
        gender = new JButton("Gender Report");
        gender.addActionListener(this);
        add(gender);
    
        age = new JButton("Age Group Report");
        age.addActionListener(this);
        add(age);
        
        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==total){
        
            pdftest();
        
        }else if(e.getSource()==gender){
        
            pdfgender();
        
        }else if(e.getSource()==age){
        
            pdfage();
        }else if(e.getSource()==back){
        
            setVisible(false);
            AdminPortal ad = new AdminPortal();
        }
        
    
    
    }
    
    public void pdftest(){

        
        
        for(int i=0; i<Run.admin.size(); i++){
        
            Run.admin.get(i).getId();
            total_admin ++;

        }
        for(int i=0; i<Run.lecturer.size(); i++){
        
            Run.lecturer.get(i).getId();
            total_lec ++;

        }
        for(int i=0; i<Run.student.size(); i++){
        
            Run.student.get(i).getId();
            total_stu ++;

        }
        try{
            
            DefaultCategoryDataset datas = new DefaultCategoryDataset();
            datas.addValue(total_admin, "Admin","Admin");
            datas.addValue(total_lec,"Lecturer","Lecturer");
            datas.addValue(total_stu,"Student","Student");
            JFreeChart cha = ChartFactory.createBarChart3D("Total Number of Entities in the Faculty","Entities", "Number", datas, PlotOrientation.VERTICAL,true,true,false);
            cha.setBackgroundPaint(Color.WHITE);
            final CategoryPlot plot = cha.getCategoryPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);

            final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            final BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);

            final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.lightGray);
            final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
            final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);
            renderer.setSeriesPaint(0, gp0);
            renderer.setSeriesPaint(1, gp1);
            renderer.setSeriesPaint(2, gp2);
            
            ChartPanel chart = new ChartPanel(cha);
            chart.setPreferredSize(new Dimension(500,270));
            OutputStream fos = new FileOutputStream("totalentities.pdf");
            

            Image image = Image.getInstance(filename);
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(485, 740);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document,fos);
            document.open();
            //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("totaluser.pdf"));
            PdfContentByte pdfContentByte = writer.getDirectContent();
            int width = 500;
            int height = 270;
            PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
            Graphics2D graphics2d = pdfTemplate.createGraphics(width, height,new DefaultFontMapper());
            java.awt.geom.Rectangle2D rectangle2d = new java.awt.geom.Rectangle2D.Double(0, 0, width, height);
 
            cha.draw(graphics2d, rectangle2d);
 
            graphics2d.dispose();
            pdfContentByte.addTemplate(pdfTemplate, 50, 80);
            
            
            
            document.add(image);
            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 24);
            cb.setColorFill(BaseColor.DARK_GRAY);
            cb.showTextAligned(Element.ALIGN_LEFT, "Total Number of Entities in the Faculty", 40, 788, 0);

            cb.endText();
            
            
            FontSelector selector = new FontSelector();
            Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 19);
            f1.setColor(BaseColor.BLACK);
            selector.addFont(f1);
            Phrase ph = selector.process(String.valueOf(total_admin));
            Phrase leh = selector.process(String.valueOf(total_lec));
            Phrase stu = selector.process(String.valueOf(total_stu));
            Phrase phtitle = selector.process("Number of Admin Staff Running in the Faculty: ");
            Phrase lectitle = selector.process("Number of Lecturer Staff Running in the Faculty:  ");
            Phrase stutitle = selector.process("Number of Student Enrolled in the Faculty:  ");
            
            PdfContentByte ab = writer.getDirectContent();
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,phtitle ,40, 700, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,ph, 200, 650, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lectitle ,40, 600, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,leh, 200, 550, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,stutitle ,40, 500, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,stu, 200, 450, 0);
            
           
            
            document.close();
            JOptionPane.showMessageDialog(this, "PDF Report Generated!");
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    
    public void pdfgender(){
    
        int male_admin = 0, female_admin = 0,female_lec =0 , male_lec =0;
        double avg_female = 0, avg_male;
          
        for(int i=0; i<Run.admin.size(); i++){
            
            String m = "Male", f = "Female";
            if(Run.admin.get(i).getGender().equals(m)){
            
                
                male_admin++;
            }
            if(Run.admin.get(i).getGender().equals(f)){
            
                
                female_admin++;
            }

        }
        for(int i=0; i<Run.lecturer.size(); i++){
            
            String m = "Male", f = "Female";
            if(Run.lecturer.get(i).getGender().equals(m)){
            
                
                male_lec++;
            }
            if(Run.lecturer.get(i).getGender().equals(f)){
            
                
                female_lec++;
            }

        }
        
        double totalstaff = male_admin+male_lec+female_admin + female_lec;
        avg_female = (female_admin + female_lec)*100 / totalstaff;
        avg_male = (male_admin + male_lec)*100 / totalstaff;
        
 
        try{
            Image image = Image.getInstance(filename);
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(485, 740);
            DefaultPieDataset def = new DefaultPieDataset();
            def.setValue("Male Staff", avg_male);
            def.setValue("Female Staff", avg_female);

            JFreeChart chart = ChartFactory.createPieChart("Average Staff Gender Percentage", def,false,false,false);
            OutputStream fos = new FileOutputStream("gender.pdf");
            
            Document gender = new Document();
            PdfWriter writer = PdfWriter.getInstance(gender,fos);
            gender.open();
            gender.add(image);
            PdfContentByte pdfContentByte = writer.getDirectContent();
            int width =500;
            int height = 270;
            PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
            Graphics2D graphics2d = pdfTemplate.createGraphics(width, height,new DefaultFontMapper());
            java.awt.geom.Rectangle2D rectangle2d = new java.awt.geom.Rectangle2D.Double(0, 0, width, height);
 
            chart.draw(graphics2d, rectangle2d);
 
            graphics2d.dispose();
            pdfContentByte.addTemplate(pdfTemplate, 50, 100);
            
            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 24);
            cb.setColorFill(BaseColor.DARK_GRAY);
            cb.showTextAligned(Element.ALIGN_CENTER, "-----Gender Report-----", 180, 788, 0);

            cb.endText();
            
            
            FontSelector selector = new FontSelector();
            Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 19);
            f1.setColor(BaseColor.BLACK);
            selector.addFont(f1);
            Phrase ph = selector.process(String.valueOf(male_admin));
            Phrase leh = selector.process(String.valueOf(female_admin));
            Phrase phtitle = selector.process("Total Number of Male Admin Staff: ");
            Phrase lectitle = selector.process("Total Number of Female Admin Staff:  ");
            
            PdfContentByte ab = writer.getDirectContent();
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,phtitle ,40, 700, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,ph, 400, 700, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lectitle ,40, 650, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,leh, 400, 650, 0);
            
            
            Phrase lecm = selector.process(String.valueOf(male_lec));
            Phrase lecf = selector.process(String.valueOf(female_lec));
            Phrase lecmale = selector.process("Total Number of Male Lecturer Staff: ");
            Phrase lecgen = selector.process("Total Number of Female Lecturer Staff:  ");
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lecmale ,40, 600, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,lecm, 400, 600, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lecgen ,40, 550, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,lecf, 400, 550, 0);
            
            Phrase avgf = selector.process(String.valueOf(df.format(avg_female)+"%"));
            Phrase avgfemale = selector.process("Average Percentage of Female Staff:  ");
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,avgfemale ,40, 500, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,avgf, 400, 500, 0);
            
            Phrase avgm = selector.process(String.valueOf(df.format(avg_male)+"%"));
            Phrase avgmale = selector.process("Average Percentage of Male Staff:  ");
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,avgmale ,40, 450, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,avgm, 400, 450, 0);
            
            
            gender.close();
            JOptionPane.showMessageDialog(this, "PDF Report Generated!");
        } catch (Exception e){
            System.out.println(e);
        }
        

    }
    
    public void pdfage(){
    
        int t1=0,t2=0,t3=0;
        int twenty=0, fourty= 0, sixty =0;
        int ltwenty=0, lfourty= 0, lsixty =0;
        for(int i=0;i<Run.admin.size();i++){
        
            if(Integer.parseInt(Run.admin.get(i).getAge())<=20){
            
                twenty++;
            
            
            }else if(Integer.parseInt(Run.admin.get(i).getAge())<=40){
            
            
                fourty++;
            }else if(Integer.parseInt(Run.admin.get(i).getAge())<=60){
            
            
                sixty++;
            }
        }
         for(int i=0;i<Run.lecturer.size();i++){
        
            if(Integer.parseInt(Run.lecturer.get(i).getAge())<=20){
            
                ltwenty++;
            
            
            }else if(Integer.parseInt(Run.lecturer.get(i).getAge())<=40){
            
            
                lfourty++;
            }else if(Integer.parseInt(Run.lecturer.get(i).getAge())<=60){
            
            
                lsixty++;
            }
        }
         t1 = twenty + ltwenty; t2 = fourty + lfourty; t3 = sixty + lsixty;
         
         
        
        try{
            
            Image image = Image.getInstance(filename);
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(485, 740);
            DefaultCategoryDataset df = new DefaultCategoryDataset();
            df.setValue(t1, "Staff", "<= 20");
            df.setValue(t2, "Staff", "<= 40");
            df.setValue(t3, "Staff", "<= 60");
            
            JFreeChart jFreeChart = ChartFactory.createBarChart("Staff Age group", "Age","Total Number",df,PlotOrientation.VERTICAL,false,false,false);
            
            OutputStream fa = new FileOutputStream("agegroup.pdf");
            
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document,fa);
            document.open();
            document.add(image);
            PdfContentByte pdfContentByte = writer.getDirectContent();
            int width = 400; 
            int height = 300; 
            PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
            Graphics2D graphics2d = pdfTemplate.createGraphics(width, height,new DefaultFontMapper());
            java.awt.geom.Rectangle2D rectangle2d = new java.awt.geom.Rectangle2D.Double(0, 0, width, height);
            jFreeChart.draw(graphics2d, rectangle2d);
 
            graphics2d.dispose();
            pdfContentByte.addTemplate(pdfTemplate, 50, 80);
            
            
            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 24);
            cb.setColorFill(BaseColor.DARK_GRAY);
            cb.showTextAligned(Element.ALIGN_LEFT, "----Age Group Report----", 40, 788, 0);

            cb.endText();
            
            
            FontSelector selector = new FontSelector();
            Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 19);
            f1.setColor(BaseColor.BLACK);
            selector.addFont(f1);
            Phrase ph = selector.process(String.valueOf(t1));
            Phrase leh = selector.process(String.valueOf(t2));
            Phrase leh2 = selector.process(String.valueOf(t3));
            Phrase phtitle = selector.process("Total staff number in age group less than and equal to 20: ");
            Phrase lectitle = selector.process("Total staff number in age group less than and equal to 40: ");
            Phrase lectitle2 = selector.process("Total staff number in age group less than and equal to 60: ");
            PdfContentByte ab = writer.getDirectContent();
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,phtitle ,40, 700, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,ph, 200, 650, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lectitle ,40, 600, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,leh, 200, 550, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lectitle2 ,40, 500, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_CENTER,leh2, 200, 450, 0);
            
           
            
            document.close();
            JOptionPane.showMessageDialog(this, "PDF Report Generated!");
        } catch (Exception e){
            System.out.println(e);
        }
    
    }
    
    
    
}

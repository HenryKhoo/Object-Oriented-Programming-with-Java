/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.awt.FlowLayout;
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
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


public class GenerateReportLec extends JFrame implements ActionListener{
    
    public JButton marks, module, back;
    public String filename = "apu.png";
    
    public GenerateReportLec(){
    
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        marks = new JButton("Student Marks Summary Report");
        marks.addActionListener(this);
        add(marks);
        
        module = new JButton("Module Report");
        module.addActionListener(this);
        add(module);
        
        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==marks){
        
            pdfmark();
        }else if(e.getSource()==module){
        
            pdfmodule();
        }else if(e.getSource()==back){
        
            setVisible(false);
            LecturePortal lecportal = new LecturePortal();
            lecportal.setVisible(true);
        }
    
    }
    
    public void pdfmark(){
        int distinction = 0, credit =0,pass =0,fail =0;
        for(int i=0;i<Run.studentmarks.size();i++){
        
            if(Run.studentmarks.get(i).getTotal() <= 49){
            
                fail++;
            
            
            }else if(Run.studentmarks.get(i).getTotal()<=64){
            
            
                pass++;
            }else if(Run.studentmarks.get(i).getTotal()<=74){
            
            
                credit++;
            }else if(Run.studentmarks.get(i).getTotal()<=100){
            
            
                distinction++;
            }
        }
         
         
        
        try{
            DefaultPieDataset defaultCategoryDataset = new DefaultPieDataset();
            defaultCategoryDataset.setValue("Fail", fail);
            defaultCategoryDataset.setValue("Pass", pass);
            defaultCategoryDataset.setValue("Credit", credit);
            defaultCategoryDataset.setValue("Distinction",distinction);
            
            JFreeChart jFreeChart = ChartFactory.createPieChart( "Student Marks", defaultCategoryDataset, false, false, false); 
                        
            OutputStream fa = new FileOutputStream("studentmarks.pdf");
            
            Image image = Image.getInstance(filename);
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(485, 740);
            
            
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document,fa);
            document.open();
            PdfContentByte pdfContentByte = writer.getDirectContent();
            int width = 400; //width of BarChart
            int height = 300; //height of BarChart
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
            cb.showTextAligned(Element.ALIGN_LEFT, "----Student Marks----", 50, 788, 0);

            cb.endText();
            
            
            FontSelector selector = new FontSelector();
            Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15);
            f1.setColor(BaseColor.BLACK);
            selector.addFont(f1);
            Phrase mark1 = selector.process(String.valueOf(fail));
            Phrase mark2 = selector.process(String.valueOf(pass));
            Phrase mark3 = selector.process(String.valueOf(credit));
            Phrase mark4 = selector.process(String.valueOf(distinction));
            Phrase marktitle1 = selector.process("Grade of 3.7-4.0 CGPA: ");
            Phrase marktitle2 = selector.process("Grade of 3.3-3.0 CGPA: ");
            Phrase marktitle3 = selector.process("Grade of 2.7-2.0 CGPA: ");
            Phrase marktitle4 = selector.process("Grade of 1.7-0.0 CGPA: ");
            
            document.add(image);
            PdfContentByte ab = writer.getDirectContent();
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,marktitle1 ,40, 700, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_RIGHT,mark4, 200, 700, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,marktitle2 ,40, 650, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_RIGHT,mark3, 200, 650, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,marktitle3 ,40, 600, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_RIGHT,mark2, 200, 600, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,marktitle4 ,40, 550, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_RIGHT,mark1, 200, 550, 0);
           
            
            document.close();
            JOptionPane.showMessageDialog(this, "PDF Report Generated!");
        } catch (Exception e){
            System.out.println(e);
        }
   }
   public void pdfmodule(){
        int level1=0, level2=0,level3=0;
        String lvl1 = "1", lvl2 = "2", lvl3 = "3";

        for(int i=0;i<Run.module.size();i++){
            String a = Run.module.get(i).getLevel();
            if(a.equals(lvl1)){
            
                level1++;

            }else if(a.equals(lvl2)){
            
            
                level2++;
            }else if(a.equals(lvl3)){
            
            
                level3++;
            }
        } 
        
        try{
            
            DefaultCategoryDataset df = new DefaultCategoryDataset();
            df.setValue(level1, "Level 1", "1");
            df.setValue(level2, "Level 2", "2");
            df.setValue(level3, "Level 3", "3");
            
            JFreeChart jFreeChart = ChartFactory.createBarChart("Number of module in each level", "Level","Number of module",df,PlotOrientation.VERTICAL,false,false,false);
            
            OutputStream fa = new FileOutputStream("module.pdf");
            
            Image image = Image.getInstance(filename);
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(485, 740);
            
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document,fa);
            document.open();
            PdfContentByte pdfContentByte = writer.getDirectContent();
            int width = 400; //width of BarChart
            int height = 300; //height of BarChart
            PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
            Graphics2D graphics2d = pdfTemplate.createGraphics(width, height,new DefaultFontMapper());
            java.awt.geom.Rectangle2D rectangle2d = new java.awt.geom.Rectangle2D.Double(0, 0, width, height);
            jFreeChart.draw(graphics2d, rectangle2d);
 
            graphics2d.dispose();
            pdfContentByte.addTemplate(pdfTemplate, 50, 80);
            
            document.add(image);
            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf, 24);
            cb.setColorFill(BaseColor.DARK_GRAY);
            cb.showTextAligned(Element.ALIGN_LEFT, "----Module Report----", 50, 788, 0);

            cb.endText();
            
            
            FontSelector selector = new FontSelector();
            Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 19);
            f1.setColor(BaseColor.BLACK);
            selector.addFont(f1);
            Phrase phlvl1 = selector.process(String.valueOf(level1));
            Phrase phlvl2 = selector.process(String.valueOf(level2));
            Phrase phlvl3 = selector.process(String.valueOf(level3));
            Phrase lvl1title = selector.process("Total number of module in level 1: ");
            Phrase lvl2title = selector.process("Total number of module in level 2: ");
            Phrase lvl3title = selector.process("Total number of module in level 3: ");
            PdfContentByte ab = writer.getDirectContent();
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lvl1title ,40, 700, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_RIGHT,phlvl1, 350, 700, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lvl2title ,40, 650, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_RIGHT,phlvl2, 350, 650, 0);
            ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,lvl3title ,40, 600, 0);
            ColumnText.showTextAligned(ab, Element.ALIGN_RIGHT,phlvl3, 350, 600, 0);
            
           
            
            document.close();
            JOptionPane.showMessageDialog(this, "PDF Report Generated!");
        } catch (Exception e){
            System.out.println(e);
        }
   }
    
    
}

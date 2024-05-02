
package Gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.sun.org.apache.xpath.internal.operations.Div;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class ViewSlot extends JFrame implements ActionListener {
    
    public ArrayList<Slotdata> slot = new ArrayList<Slotdata>();
    public JFrame fra = new JFrame();
    public DefaultTableModel z;
    public JTable z1; public JPanel panel; public JButton back;
    public DateTimePicker dates = new DateTimePicker();
    public String searchd; public JLabel se;
    public String ha; public JTextField searchstu1;
    
    
    public ViewSlot(){
        
        getSlotArray();
        fra.setSize(850, 500);
        fra.setLayout(null);
        fra.setLocationRelativeTo(null);
        fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        
        back = new JButton("Back");
        back.setBounds(340, 420, 100, 25);
        back.addActionListener(this);
       
    
        panel.setBounds(100, 50, 250, 100);
        
        
        
        int size = slot.size();
        String[][] data = new String[size][6];
        for(int i=0; i<size; i++){
        
            Slotdata f = slot.get(i);
            data[i][0] = f.getName();
            data[i][1] = ""+f.getModule();
            data[i][2] = f.getDate();
            data[i][3] = f.getTime();
            data[i][4] = f.getHour();
            data[i][5] = f.getVenue();     
        }
        searchstu1 = new JTextField();
        searchstu1.setBounds(320, 30, 200, 25);
        
        se = new JLabel("Search: ");
        se.setBounds(250, 30, 150, 25);
        //searchd = datePicker.getText();
        
        
        //searchstu1.setText(datePicker.getText());
        
        searchstu1.addKeyListener(new KeyAdapter(){
        
        @Override
            public void keyReleased(KeyEvent evt) {
                searchTFKeyReleased(evt);
            }
        
        });
        
        
        
        
        
        String[] columnNames = { "Lecturer Name", "Module", "Date", "Time" , "Hour Duration","Venue"};
        z = new DefaultTableModel(data, columnNames);
        z1 = new JTable(z);
        z1.setPreferredScrollableViewportSize(z1.getPreferredSize());
        JScrollPane sp = new JScrollPane(z1);
        sp.setBounds(25, 100, 750, 300);
        fra.add(sp); fra.add(panel); fra.add(back);fra.add(searchstu1);fra.add(searchstu1);fra.add(se);
        fra.setVisible(true);

    }
    
    public void actionPerformed(ActionEvent ee){

        if(ee.getSource()==back){
            

            Menu me = new Menu();
            fra.dispose();
        }
            
    }
    
    
    
    
    class Slotdata {
    
    
        String name, date, time,module,hour,venue;

        public Slotdata(String name, String date, String time, String module, String hour, String venue) {
            this.name = name;
            this.date = date;
            this.time = time;
            this.module = module;
            this.hour = hour;
            this.venue = venue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

    
    }
    public void getSlotArray(){
    try{
            
            Scanner input = new Scanner(new File("consultation.txt"));

            while(input.hasNext()){

                String  name= input.nextLine();
                String  dat = input.nextLine();
                String  ti = input.nextLine();
                String mo = input.nextLine();
                String hr = input.nextLine();
                String v = input.nextLine();
                input.nextLine();
                Slotdata slot1 = new Slotdata(name,dat,ti,mo,hr,v);
                slot.add(slot1);

            }
            
            }catch(Exception e){
                System.out.println("Operation cancelled");
        }
    
    }
    
    public void searchTFKeyReleased(KeyEvent evt){
        DefaultTableModel model = (DefaultTableModel)z1.getModel();
        String search = searchstu1.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        z1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }
}

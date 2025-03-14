
package com.raven.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;



public class Panelcover extends javax.swing.JPanel {

       private ActionListener event ;
        public Panelcover() {
        initComponents();
        setOpaque(false) ;
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("Test animation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jButton1)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(96, 96, 96))
        );
    }// </editor-fold>//GEN-END:initComponents
   
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           
        System.out.println("Checking event: " + event);
        
             event.actionPerformed(evt);
               if (event != null) { // Check if event is initialized before calling it
            event.actionPerformed(evt);
        } else {
            System.out.println("Error: event is null!");
        }
             
    }//GEN-LAST:event_jButton1ActionPerformed

     @Override 
    protected void paintComponent (Graphics grphcs)
     {
         Graphics2D g2 = (Graphics2D) grphcs ;
         GradientPaint gra = new GradientPaint(0,0 , new Color(134, 51, 255) ,0 ,getHeight() , new Color (94, 51, 255)) ;
         g2.setPaint(gra);
         
         g2.fillRect(0,0,getWidth(),getHeight());
         super.paintComponent(grphcs) ;
     }
    
       public void addEvent(ActionListener event){
                   
            this.event = event;
       }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}

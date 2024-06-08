package com.TicTacToe;

import com.TicTacToe.Caro.Play2PlayersCaro;
import com.TicTacToe.Caro.PlayWithAiCaro;
import com.TicTacToe.Connect4.Play2PlayersConnect4;
import com.TicTacToe.Connect4.PlayWithAiConnect4;
import com.TicTacToe.TicTacToe.Play2Players;
import com.TicTacToe.TicTacToe.PlayWithAI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameMain {
    public static JFrame jFrame;
    private JPanel JPanelMain;
    private JButton btnPlay2Players;
    private JButton btnPlayVSAI;
    private JTextField txtPlayer1Name;
    private JTextField txtPlayer2Name;
    private JButton btnExit;
    private JCheckBox cbxModChap1Buoc;
    private JSpinner spinnerRow;

    public JFrameMain() {
        // Cài đặt model cho spinner nhập dòng, cột.
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(10, //initial value
                        3, //min
                        20, //max
                        1);//step
        spinnerRow.setModel(spinnerModel);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnPlayVSAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Player1Name = txtPlayer1Name.getText();
                if(Player1Name.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên người chơi!!!");
                    return;
                }
                int row = (int)spinnerRow.getValue();
                if( row<3 || row >20){
                    JOptionPane.showMessageDialog(null,"Bạn phải nhập số dòng, cột nằm trong khoảng" +
                            " giá trị từ 3 đến 20!!!");
                    return;
                }
                // Chọn độ khó máy
                Object[] options1 = {"Dễ",
                        "Bình thường"};
                int resultLevel = JOptionPane.showOptionDialog(null,"Chọn độ khó","Chọn độ khó" +
                        "",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options1,options1[1]);


                    if(row>=5){
                        if(resultLevel == JOptionPane.YES_OPTION){
                            PlayWithAiCaro.GameBot = PlayWithAI.Bot.EASY_BOT;
                        }
                        else PlayWithAiCaro.GameBot = PlayWithAI.Bot.HEURISTIC_BOT;
                        PlayWithAiCaro.newRow =row;
                        PlayWithAiCaro heuristicBotCaro = new PlayWithAiCaro(Player1Name);
                    }
                    else if (row ==3){
                        if(resultLevel == JOptionPane.YES_OPTION){
                            PlayWithAI.GameBot = PlayWithAI.Bot.EASY_BOT;
                        }
                        else PlayWithAI.GameBot = PlayWithAI.Bot.HEURISTIC_BOT;
                        PlayWithAI.newRow = row;
                        new PlayWithAI(Player1Name);
                    }
                    else if(row ==4){
                        if(resultLevel == JOptionPane.YES_OPTION){
                            PlayWithAiConnect4.GameBot = PlayWithAI.Bot.EASY_BOT;
                        }
                        else PlayWithAiConnect4.GameBot = PlayWithAI.Bot.HEURISTIC_BOT;
                        PlayWithAiConnect4.newRow =row;
                        PlayWithAiConnect4 heuristicBotCaro = new PlayWithAiConnect4(Player1Name);
                    }

                jFrame.setVisible(false);
            }
        });
        btnPlay2Players.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Player1Name = txtPlayer1Name.getText();
                String Player2Name = txtPlayer2Name.getText();
                if(Player1Name.isEmpty() || Player2Name.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập đủ tên 2 người chơi!!!");
                    return;
                }
                int row = (int)spinnerRow.getValue();
                if( row<3 || row >20){
                    JOptionPane.showMessageDialog(null,"Bạn phải nhập số dòng, cột nằm trong khoảng" +
                            " giá trị từ 3 đến 20!!!");
                    return;
                }
                else{
                    if(row>=5){
                        Play2PlayersCaro.newRow = row;
                        new Play2PlayersCaro(Player1Name,Player2Name);
                    }
                    else if(row==3) {
                        Play2Players.newRow = row;
                        new Play2Players(Player1Name,Player2Name);
                    }
                    else if (row==4){
                        Play2PlayersConnect4.newRow =row;
                        new Play2PlayersConnect4(Player1Name,Player2Name);
                    }
                }
                jFrame.setVisible(false);
            }
        });
    }

    public void CreateAndShow(){
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(3, //initial value
                        3, //min
                        20, //max
                        1);//step
        spinnerRow.setModel(spinnerModel);
        jFrame =new JFrame("Tic Tac Toe");
        jFrame.setContentPane(new JFrameMain().JPanelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);

    }


}

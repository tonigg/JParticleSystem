package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import particle.ParticleEmiter;

public class EmiterGUI extends JFrame implements ActionListener{

	ParticleEmiter emiter;
	private JTextField minVelField;
	private JTextField maxVelField;
	
	public EmiterGUI(ParticleEmiter emiter) {
		this.emiter = emiter;
		
		setTitle(emiter.getTitle());
		setSize(200, 400);
		
		JPanel panel = new JPanel();
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		
		JPanel velPanel = new JPanel(new FlowLayout());
				
		minVelField = new JTextField();
		minVelField.setEditable(true);
		minVelField.setPreferredSize(new Dimension(20, 20));
		
		maxVelField = new JTextField();
		maxVelField.setEditable(true);
		maxVelField.setPreferredSize(new Dimension(20, 20));
		
		velPanel.add(new JLabel("Velocity"), FlowLayout.LEFT);
		velPanel.add(minVelField, FlowLayout.CENTER);
		velPanel.add(new JSeparator(SwingConstants.VERTICAL));
		velPanel.add(maxVelField, FlowLayout.CENTER);
		
		JPanel colPanel = new JPanel(new FlowLayout());
		
		colPanel.add(new JLabel("Color"), FlowLayout.LEFT);
		
		inputPanel.add(velPanel, Component.CENTER_ALIGNMENT);
		inputPanel.add(colPanel, Component.CENTER_ALIGNMENT);
		panel.add(new JLabel(emiter.getTitle()), BorderLayout.CENTER);

		add(panel, BorderLayout.NORTH);
		add(inputPanel, BorderLayout.CENTER);
		JButton button = new JButton("Set new parameters");
		button.addActionListener(this);
		add(button, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	private void saveData() {
		System.out.println("assssasfas");
		int minVelocity = Integer.parseInt(minVelField.getText());
		int maxVelocity = Integer.parseInt(maxVelField.getText());
		
		emiter.setMinVelocity(new Point(minVelocity, minVelocity));
		emiter.setMaxVelocity(new Point(maxVelocity, maxVelocity));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Set new parameters")){
			saveData();
		}
	}

}

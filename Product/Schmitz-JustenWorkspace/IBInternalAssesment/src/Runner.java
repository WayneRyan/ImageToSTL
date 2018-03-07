import java.awt.Button;
import java.awt.Label;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class Runner extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800, HEIGHT = 800;
	private static Button selectImage1, selectImage2, writeOutput;
	private static Button updateImage1, updateImage2, overwrite;
	private static Button resetOrbit, smooth;
	private static JSlider sliderX, sliderY, sliderZ;
	public static InputFile inputFile1, inputFile2;
	public static Model myModel;

	public static void main(String[] args) {
		Runner myRunner = new Runner();
		myRunner.setDefaultCloseOperation(EXIT_ON_CLOSE);
		myRunner.setVisible(true);
	}

	/**
	 * Sets up the JFrame without a layout manager Adds actionListener to
	 * buttons
	 */
	public Runner() {
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(null);
		selectImage1 = new Button("Select");
		selectImage2 = new Button("Select");
		writeOutput = new Button("Write Output");
		updateImage1 = new Button("Update");
		updateImage2 = new Button("Update");
		overwrite = new Button("Overwrite Output");
		resetOrbit = new Button("Reset Orbit");
		smooth = new Button("Smooth");
		
		sliderX = new JSlider(JSlider.HORIZONTAL, 1, 120, 60);
		sliderY = new JSlider(JSlider.HORIZONTAL, 1, 120, 60);
		sliderZ = new JSlider(JSlider.HORIZONTAL, 1, 120, 60);
		sliderX.setName("silderX");
		sliderY.setName("silderY");
		sliderZ.setName("silderZ");
		
		Label temp = new Label("Scale X");
		temp.setBounds(30,10,75,20);
		this.add(temp);
		temp = new Label("Scale Y");
		temp.setBounds(30,35,75,20);
		this.add(temp);
		temp = new Label("Scale Z");
		temp.setBounds(30,60,75,20);
		
		this.add(temp);
		sliderX.setBounds(100, 10, 600, 20);
		this.add(sliderX);
		sliderY.setBounds(100, 35, 600, 20);
		this.add(sliderY);
		sliderZ.setBounds(95, 60, 610, 45);
		Hashtable<Integer,JLabel> labels = new Hashtable<Integer,JLabel>();
		labels.put(1, new JLabel(".1"));
		for(int i=1; i<13; i++){
			labels.put(i*10, new JLabel(""+i));
		}
		sliderZ.setLabelTable(labels);
		sliderZ.setPaintLabels(true);
		sliderZ.setMajorTickSpacing(10);
		sliderZ.setMinorTickSpacing(2);
		sliderZ.setPaintTicks(true);
		this.add(sliderZ);
		
		temp = new Label("Top Image");
		temp.setBounds(100, 100, 100, 20);
		this.add(temp);
		selectImage1.setBounds(10, 120, 120, 20);
		updateImage1.setBounds(135, 120, 125, 20);
		inputFile1 = new InputFile();
		inputFile1.setBounds(10, 150, 250, 250);
		this.add(inputFile1);
		selectImage1.addActionListener(inputFile1);
		updateImage1.addActionListener(inputFile1);
		
		
		temp = new Label("Bottom Image");
		temp.setBounds(100, 420, 100, 20);
		this.add(temp);
		selectImage2.setBounds(10, 440, 120, 20);
		updateImage2.setBounds(135, 440, 125, 20);
		inputFile2 = new InputFile();
		inputFile2.setBounds(10, 470, 250, 250);
		this.add(inputFile2);
		selectImage2.addActionListener(inputFile2);
		updateImage2.addActionListener(inputFile2);
		
		
		writeOutput.setBounds(270, 120, 125, 20);
		overwrite.setBounds(395, 120, 125, 20);
		smooth.setBounds(520,120,125,20);
		resetOrbit.setBounds(645,120, 125, 20);
		myModel = new Model();
		myModel.setBounds(270, 150, 500, 500);
		writeOutput.addActionListener(myModel);
		overwrite.addActionListener(myModel);
		resetOrbit.addActionListener(myModel);
		smooth.addActionListener(myModel);
		sliderX.addChangeListener(myModel);
		sliderY.addChangeListener(myModel);
		sliderZ.addChangeListener(myModel);
		this.add(myModel);
		
		
		
		
		
		
		selectImage2.setEnabled(false);
		writeOutput.setEnabled(false);
		updateImage1.setEnabled(false);
		updateImage2.setEnabled(false);
		overwrite.setEnabled(false);
		smooth.setEnabled(false);
		this.add(selectImage1);
		this.add(selectImage2);
		this.add(writeOutput);
		this.add(updateImage1);
		this.add(updateImage2);
		this.add(overwrite);
		this.add(resetOrbit);
		this.add(smooth);

		

		


		
	}

	
	/**
	 * Enables/Disables buttons and tells model to recompute
	 * Called whenever an InputFile is changed
	 */
	public static void update() {
		boolean hasImageOne = inputFile1.hasImage();
		if (!hasImageOne){ 
			inputFile2.setImageNull();
			myModel.clearRendering();
		}
		boolean hasImageTwo = inputFile2.hasImage();
		selectImage2.setEnabled(hasImageOne);
		writeOutput.setEnabled(hasImageOne);
		updateImage1.setEnabled(hasImageOne);
		smooth.setEnabled(hasImageOne);
		
		updateImage2.setEnabled(hasImageTwo);
		overwrite.setEnabled(myModel.hasPath());
		if (hasImageOne && hasImageTwo
				&& (  inputFile1.getImageHeight() != inputFile2.getImageHeight()
				|| inputFile1.getImageWidth() != inputFile2.getImageWidth())) {
			JOptionPane.showMessageDialog(null,
					"Images have different sizes. Final output may be hard to predict.");
		}
		if (hasImageOne)myModel.recompute();

	}
}

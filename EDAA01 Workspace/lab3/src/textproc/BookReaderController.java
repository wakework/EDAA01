package textproc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.ButtonGroup;
//import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BookReaderController {
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// pane är en behållarkomponent till vilken de övriga komponenterna (listvy,
		// knappar etc.) ska läggas till.
		Container pane = frame.getContentPane();

		// Panel för knappar.
		JPanel panel = new JPanel();
		// V4
		JRadioButton button = new JRadioButton("Alphabetical");
		JRadioButton button2 = new JRadioButton("Frequency");
		JRadioButton search = new JRadioButton("Search");
		ButtonGroup group = new ButtonGroup();
		JTextField textField = new JTextField(10);

		group.add(button);
		group.add(button2);
		group.add(search);
		panel.add(button);
		panel.add(button2);
		panel.add(textField);
		panel.add(search);

		// Listmodel för listan i fråga.
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(counter.getWordList());

		// Skapa listan.
		JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);
		listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Trycka på knappar.
		button.addActionListener(event -> {
			listModel.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		});

		button2.addActionListener(event -> {
			listModel.sort((e1, e2) -> e2.getValue() - e1.getValue());
		});

		search.addActionListener(event -> {
			// V1
			String word = textField.getText().toLowerCase();
			String resultString = word.trim();
			int i = 0;

			while (i < listModel.getSize()) {
				if (resultString.compareTo(listModel.getElementAt(i).getKey()) == 0) {
					listView.ensureIndexIsVisible(i);
					listView.setSelectedIndex(i);
					listView.setSelectionBackground(Color.lightGray);

					break;
				}
				i++;
			}
			// V2
			if (i >= listModel.getSize()) {
				JOptionPane.showMessageDialog(pane, "Word does not exist in list");
			}
		});

		// Scroller i listan.
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(500, 250));
		scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10));

		// Dela upp behållarkomponenten.
		pane.add(scrollPane);
		pane.add(panel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}
}

package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControleCampeonato;
import model.Atleta;
import model.Pais;
import model.Prova;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Campeonato extends JFrame {

	private static final long serialVersionUID = 6628613480798698487L;
	private JPanel contentPane;
	private JPanel panelResultado;
	private JPanel panelEvento;
	private JPanel panelMundial;
	private JTextField tfNomePais;
	private JTextField tfCoiPais;
	private JTextField tfNomeAt;
	private JTextField tfMarca;
	private JComboBox<String> cBoxBateria;
	private JComboBox<String> cBoxSexoAt;
	private JComboBox<Pais> cBoxPaisAt;
	private JComboBox<Atleta> cBoxAtleta;
	private JComboBox<Prova> cBoxProvas; 
	private JComboBox<Prova> cBoxProvasResult; 
	private JComboBox<Prova> cBoxProvasRanking; 
	private JRadioButton rdbtnFaseInicial, rdbtnFaseFinal, rdbtnTempo, rdbtnDistancia;
	private JLabel lblBateria;
	private JTextField tfNomeProva;
	private JTextField tfSexoProva;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Campeonato frame = new Campeonato();
					frame.setVisible(true);
					ControleCampeonato.ListarCombos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Campeonato() {
		// ------- ********** frame e tabbedpane ********** -------
		this.setTitle("Campeonato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 0, 850, 600);
		contentPane.add(tabbedPane);

		// ------- ********** Painel Pais ********** -------
		JPanel panelPais = new JPanel();
		tabbedPane.addTab("Pais", null, panelPais, null);
		panelPais.setLayout(null);

		//---- Label  "Adicionar paises ao campeonato"
		JLabel lblAdicionarPaisesAo = new JLabel("   Adicionar paises ao campeonato");
		lblAdicionarPaisesAo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdicionarPaisesAo.setBounds(0, 21, 278, 53);
		panelPais.add(lblAdicionarPaisesAo);

		//---- Label  "nome"
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 85, 56, 14);
		panelPais.add(lblNome);
		
		//---- Textfield Nome do pais
		tfNomePais = new JTextField();
		tfNomePais.setBounds(62, 82, 175, 20);
		panelPais.add(tfNomePais);
		tfNomePais.setColumns(10);

		//---- Label  "Coi"
		JLabel lblCoi = new JLabel("Coi:");
		lblCoi.setBounds(20, 130, 46, 14);
		panelPais.add(lblCoi);

		//---- Textfield COI pais
		tfCoiPais = new JTextField();
		tfCoiPais.setBounds(64, 127, 86, 20);
		panelPais.add(tfCoiPais);
		tfCoiPais.setColumns(10);

		//---- Botao  "Adicionar" aciona: AdicionarPais
		JButton btnAddPais = new JButton("Adicionar");
		btnAddPais.setBounds(74, 243, 89, 23);
		btnAddPais.setActionCommand("AdicionarPais");
		panelPais.add(btnAddPais);

		//---- Label  "Lista de paises que est\u00E3o no campeonato"
		JLabel lblListaDePaises = new JLabel("Lista de paises que est\u00E3o no campeonato");
		lblListaDePaises.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListaDePaises.setBounds(291, 21, 278, 53);
		panelPais.add(lblListaDePaises);
		
		// Tabela paises 
		TabelaPaises.ChamarTblPaises(panelPais);

		// ------- ********** Painel Prova ********** -------
		JPanel panelProva = new JPanel();
		tabbedPane.addTab("Prova", null, panelProva, null);
		panelProva.setLayout(null);

		//---- Label  "   Adicionar provas ao campeonato"
		JLabel lblAdicionarProvasAo = new JLabel("   Adicionar provas ao campeonato");
		lblAdicionarProvasAo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdicionarProvasAo.setBounds(0, 22, 278, 53);
		panelProva.add(lblAdicionarProvasAo);

		//---- Textfield Nome da prova
		tfNomeProva = new JTextField();
		tfNomeProva.setColumns(10);
		tfNomeProva.setBounds(62, 83, 175, 20);
		panelProva.add(tfNomeProva);

		tfSexoProva = new JTextField();
		tfSexoProva.setColumns(10);
		tfSexoProva.setBounds(62, 128, 86, 20);
		panelProva.add(tfSexoProva);

		JLabel lblSexo_1 = new JLabel("Sexo:");
		lblSexo_1.setBounds(10, 131, 46, 14);
		panelProva.add(lblSexo_1);

		JLabel label_2 = new JLabel("Nome:");
		label_2.setBounds(10, 86, 56, 14);
		panelProva.add(label_2);

		JButton btnAddProva = new JButton("Adicionar");
		btnAddProva.setActionCommand("AdicionarPais");
		btnAddProva.setBounds(74, 244, 89, 23);
		btnAddProva.setActionCommand("AdicionarProva");
		panelProva.add(btnAddProva);
		TabelaProva.ChamarTblProva(panelProva);

		JLabel lblListaDeProvas = new JLabel("Lista de provas que est\u00E3o no campeonato");
		lblListaDeProvas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListaDeProvas.setBounds(291, 22, 278, 53);
		panelProva.add(lblListaDeProvas);

		// ------- ********** Painel Atleta ********** -------
		JPanel panelAtleta = new JPanel();
		tabbedPane.addTab("Atleta", null, panelAtleta, null);
		panelAtleta.setLayout(null);

		JLabel lblAdicionarAtletasAo = new JLabel("   Adicionar atletas ao campeonato");
		lblAdicionarAtletasAo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdicionarAtletasAo.setBounds(0, 22, 278, 53);
		panelAtleta.add(lblAdicionarAtletasAo);

		JLabel lblListaDeAtletas = new JLabel("Lista de atletas que est\u00E3o no campeonato");
		lblListaDeAtletas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListaDeAtletas.setBounds(291, 22, 278, 53);
		panelAtleta.add(lblListaDeAtletas);

		TabelaAtleta.ChamarTblAtleta(panelAtleta);

		tfNomeAt = new JTextField();
		tfNomeAt.setColumns(10);
		tfNomeAt.setBounds(63, 83, 175, 20);
		panelAtleta.add(tfNomeAt);

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(10, 174, 46, 14);
		panelAtleta.add(lblPais);

		JLabel label_4 = new JLabel("Nome:");
		label_4.setBounds(10, 86, 56, 14);
		panelAtleta.add(label_4);

		JButton btnAddAt = new JButton("Adicionar");
		btnAddAt.setBounds(74, 244, 89, 23);
		btnAddAt.setActionCommand("AdicionarAtleta");
		panelAtleta.add(btnAddAt);

		cBoxSexoAt = new JComboBox<String>();
		cBoxSexoAt.setBounds(63, 125, 131, 20);
		panelAtleta.add(cBoxSexoAt);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(10, 128, 46, 14);
		panelAtleta.add(lblSexo);

		cBoxPaisAt = new JComboBox<Pais>();
		cBoxPaisAt.setBounds(63, 171, 131, 20);
		panelAtleta.add(cBoxPaisAt);

		// ------- ********** Painel Marca ********** -------
		JPanel panelMarca = new JPanel();
		tabbedPane.addTab("Marca", null, panelMarca, null);
		panelMarca.setLayout(null);

		JLabel lblAdicionarResultadosDos = new JLabel("   Adicionar resultados dos atletas");
		lblAdicionarResultadosDos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdicionarResultadosDos.setBounds(53, 30, 278, 53);
		panelMarca.add(lblAdicionarResultadosDos);

		JLabel lblAtleta = new JLabel("Atleta:");
		lblAtleta.setBounds(63, 94, 46, 14);
		panelMarca.add(lblAtleta);

		cBoxAtleta = new JComboBox<Atleta>();
		cBoxAtleta.setBounds(109, 94, 222, 20);
		panelMarca.add(cBoxAtleta);

		JLabel lblFase = new JLabel("Fase:");
		lblFase.setBounds(63, 135, 46, 14);
		panelMarca.add(lblFase);

		rdbtnFaseInicial = new JRadioButton("Inicial");
		rdbtnFaseInicial.setBounds(100, 131, 67, 23);
		rdbtnFaseInicial.setSelected(true);
		panelMarca.add(rdbtnFaseInicial);

		rdbtnFaseFinal = new JRadioButton("Final");
		rdbtnFaseFinal.setBounds(170, 131, 109, 23);
		panelMarca.add(rdbtnFaseFinal);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFaseFinal);
		bg.add(rdbtnFaseInicial);

		JLabel lblProva = new JLabel("Prova:");
		lblProva.setBounds(63, 173, 46, 14);
		panelMarca.add(lblProva);

		cBoxProvas = new JComboBox<Prova>();
		cBoxProvas.setBounds(109, 170, 222, 20);
		panelMarca.add(cBoxProvas);

		JLabel lblTipoDeResultado = new JLabel("Tipo de Marca:");
		lblTipoDeResultado.setBounds(63, 217, 97, 14);
		panelMarca.add(lblTipoDeResultado);

		rdbtnTempo = new JRadioButton("Tempo");
		rdbtnTempo.setBounds(152, 213, 67, 23);
		rdbtnTempo.setSelected(true);
		panelMarca.add(rdbtnTempo);

		rdbtnDistancia = new JRadioButton("Distancia");
		rdbtnDistancia.setBounds(221, 213, 79, 23);
		panelMarca.add(rdbtnDistancia);

		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(rdbtnTempo);
		bg2.add(rdbtnDistancia);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(63, 257, 46, 14);
		panelMarca.add(lblMarca);

		tfMarca = new JTextField();
		tfMarca.setBounds(110, 254, 78, 20);
		panelMarca.add(tfMarca);
		tfMarca.setColumns(10);

		lblBateria = new JLabel("Bateria:");
		lblBateria.setBounds(63, 289, 46, 14);
		lblBateria.setVisible(false);
		panelMarca.add(lblBateria);

		cBoxBateria = new JComboBox<String>();
		cBoxBateria.setBounds(109, 286, 79, 20);
		panelMarca.add(cBoxBateria);
		cBoxBateria.setVisible(false);

		// ------- ********** Painel Resultado ********** -------
		panelResultado = new JPanel();
		tabbedPane.addTab("Resultado", null, panelResultado, null);
		panelResultado.setLayout(null);

		JLabel lblProvaResult = new JLabel("Prova:");
		lblProvaResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProvaResult.setBounds(26, 14, 80, 34);
		panelResultado.add(lblProvaResult);
		
		cBoxProvasResult = new JComboBox<Prova>();
		cBoxProvasResult.setBounds(121, 23, 222, 20);
		panelResultado.add(cBoxProvasResult);		

		JLabel lblListaFaseInicial = new JLabel("Lista fase inicial");
		lblListaFaseInicial.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListaFaseInicial.setBounds(26, 64, 206, 53);
		panelResultado.add(lblListaFaseInicial);

		JLabel lblListaFaseFinal = new JLabel("Lista fase final");
		lblListaFaseFinal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListaFaseFinal.setBounds(427, 64, 206, 53);
		panelResultado.add(lblListaFaseFinal);
		
		JButton btnBusca_provaresult = new JButton("Buscar");
		btnBusca_provaresult.setActionCommand("Busca_provaresult");	
		btnBusca_provaresult.setBounds(377, 19, 89, 23);
		panelResultado.add(btnBusca_provaresult);

		// ------- ********** Painel Ranking ********** -------
		JPanel panelRanking = new JPanel();
		tabbedPane.addTab("Ranking", null, panelRanking, null);
		panelRanking.setLayout(null);
		
		JButton btnRanking = new JButton("Atualizar");
		btnRanking.setActionCommand("BuscaRanking");	
		btnRanking.setBounds(377, 19, 89, 23);
		panelRanking.add(btnRanking);

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 61, 845, 505);
		panelRanking.add(tabbedPane_2);

		panelEvento = new JPanel();
		tabbedPane_2.addTab("Evento", null, panelEvento, null);
		panelEvento.setLayout(null);

		panelMundial = new JPanel();
		tabbedPane_2.addTab("Mundial", null, panelMundial, null);
		panelMundial.setLayout(null);

		JLabel lblProvaranking = new JLabel("Prova:");
		lblProvaranking.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProvaranking.setBounds(26, 14, 80, 34);
		panelRanking.add(lblProvaranking);
		
		cBoxProvasRanking = new JComboBox<Prova>();
		cBoxProvasRanking.setBounds(109, 20, 222, 20);
		panelRanking.add(cBoxProvasRanking);		

		ControleCampeonato controle = new ControleCampeonato(cBoxSexoAt, cBoxPaisAt, cBoxAtleta, cBoxProvas, tfNomePais,
				tfCoiPais, tfNomeAt, tfMarca, cBoxBateria, tfNomeProva, tfSexoProva, rdbtnFaseInicial, rdbtnFaseFinal,
				rdbtnTempo, rdbtnDistancia, lblBateria, cBoxProvasResult, cBoxProvasRanking, panelResultado, panelEvento, panelMundial);
		


		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(242, 285, 89, 23);
		btnEnviar.setActionCommand("EnviarMarca");
		panelMarca.add(btnEnviar);

		btnEnviar.addActionListener(controle);
		btnAddAt.addActionListener(controle);
		btnAddPais.addActionListener(controle);
		btnAddProva.addActionListener(controle);
		btnBusca_provaresult.addActionListener(controle);
		btnRanking.addActionListener(controle);
		rdbtnDistancia.addActionListener(controle);
		rdbtnTempo.addActionListener(controle);

	}
}

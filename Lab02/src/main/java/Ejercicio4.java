import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Ejercicio4 extends JFrame {
    private JTextField txtMarca, txtModelo, txtColor, txtKilometraje;
    private JButton btnAgregar, btnOrdenarModelo, btnOrdenarKilometraje, btnSalir;
    private JTextArea txtAreaResultado;
    private Carro[] carros;
    private int contador;

    public Ejercicio4() {
        setTitle("Gestor de Carros Usados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelEntrada = new JPanel(new GridLayout(5, 2));
        panelEntrada.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panelEntrada.add(txtMarca);

        panelEntrada.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelEntrada.add(txtModelo);

        panelEntrada.add(new JLabel("Color:"));
        txtColor = new JTextField();
        panelEntrada.add(txtColor);

        panelEntrada.add(new JLabel("Kilometraje:"));
        txtKilometraje = new JTextField();
        panelEntrada.add(txtKilometraje);

        btnAgregar = new JButton("Agregar Carro");
        panelEntrada.add(btnAgregar);

        add(panelEntrada, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        btnOrdenarModelo = new JButton("Ordenar por Modelo");
        btnOrdenarKilometraje = new JButton("Ordenar por Kilometraje");
        btnSalir = new JButton("Salir");
        panelBotones.add(btnOrdenarModelo);
        panelBotones.add(btnOrdenarKilometraje);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setPreferredSize(new Dimension(500, 200)); // Establecer el tamaño preferido
        JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
        add(scrollPane, BorderLayout.SOUTH);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCarro();
            }
        });

        btnOrdenarModelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorModelo();
            }
        });

        btnOrdenarKilometraje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarPorKilometraje();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        carros = new Carro[100];
        contador = 0;
    }

    private void agregarCarro() {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String color = txtColor.getText();
        int kilometraje = Integer.parseInt(txtKilometraje.getText());

        carros[contador] = new Carro(marca, modelo, color, kilometraje);
        contador++;

        txtAreaResultado.setText("Carro agregado: " + marca + " " + modelo + " " + color + " " + kilometraje + "\n");
    }

    private void ordenarPorModelo() {
        Carro[] carrosModelo = Arrays.copyOf(carros, contador);
        bubbleSortModelo(carrosModelo);
        mostrarCarros(carrosModelo);
    }

    private void ordenarPorKilometraje() {
        Carro[] carrosKilometraje = Arrays.copyOf(carros, contador);
        mergeSortKilometraje(carrosKilometraje, 0, carrosKilometraje.length - 1);
        mostrarCarros(carrosKilometraje);
    }

    private void mostrarCarros(Carro[] carros) {
        StringBuilder sb = new StringBuilder();
        for (Carro carro : carros) {
            sb.append(carro).append("\n");
        }
        txtAreaResultado.setText(sb.toString());
    }

    private void bubbleSortModelo(Carro[] carros) {
        for (int i = 0; i < carros.length - 1; i++) {
            for (int j = 0; j < carros.length - i - 1; j++) {
                if (carros[j].getModelo().compareTo(carros[j + 1].getModelo()) > 0) {
                    Carro temp = carros[j];
                    carros[j] = carros[j + 1];
                    carros[j + 1] = temp;
                }
            }
        }
    }

    private void mergeSortKilometraje(Carro[] carros, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            mergeSortKilometraje(carros, izquierda, medio);
            mergeSortKilometraje(carros, medio + 1, derecha);
            merge(carros, izquierda, medio, derecha);
        }
    }

    private void merge(Carro[] carros, int izquierda, int medio, int derecha) {
        Carro[] temp = new Carro[derecha - izquierda + 1];
        int i = izquierda, j = medio + 1, k = 0;

        while (i <= medio && j <= derecha) {
            if (carros[i].getKilometraje() <= carros[j].getKilometraje()) {
                temp[k++] = carros[i++];
            } else {
                temp[k++] = carros[j++];
            }
        }

        while (i <= medio) {
            temp[k++] = carros[i++];
        }

        while (j <= derecha) {
            temp[k++] = carros[j++];
        }

        System.arraycopy(temp, 0, carros, izquierda, temp.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio4().setVisible(true));
    }
}

class Carro {
    private String marca;
    private String modelo;
    private String color;
    private int kilometraje;

    public Carro(String marca, String modelo, String color, int kilometraje) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.kilometraje = kilometraje;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " " + color + " " + kilometraje;
    }
}

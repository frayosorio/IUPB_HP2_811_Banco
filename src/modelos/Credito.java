package modelos;

import java.text.DecimalFormat;

public class Credito extends Cuenta {

    private double valorPrestado;
    private double tasaInteres;
    private int plazo;
    private double saldoRetiro;

    public Credito(String titular, String numero, double valorPrestado, double tasaInteres, int plazo) {
        super(titular, numero);
        this.valorPrestado = valorPrestado;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        this.saldoRetiro = valorPrestado;
    }

    public double getValorPrestado() {
        return valorPrestado;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public int getPlazo() {
        return plazo;
    }

    public double getSaldoRetiro() {
        return saldoRetiro;
    }

    public double getSaldoDeuda() {
        return valorPrestado - getSaldo();
    }

    @Override
    public boolean retirar(double cantidad) {
        if (saldoRetiro >= cantidad) {
            saldoRetiro -= cantidad;
            return true;
        }
        return false;
    }

    public void pagar(double cantidad) {
        if (getSaldo() < valorPrestado) {
            var intereses = getSaldoDeuda() * tasaInteres / 100;
            var abonoCapital = cantidad - intereses;
            setSaldo(getSaldo() + abonoCapital);
        }
    }

    public double getCuota() {
        double tasaReal = tasaInteres / 100;
        return valorPrestado * Math.pow(1 + tasaReal, plazo) * tasaReal / (Math.pow(1 + tasaReal, plazo) - 1);
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");

        return new String[] { "Crédito",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                "Valor Prestado= $" + df.format(valorPrestado) +
                        "Tasa " + df.format(tasaInteres) + " % Plazo " + plazo + " meses" };

    }

}

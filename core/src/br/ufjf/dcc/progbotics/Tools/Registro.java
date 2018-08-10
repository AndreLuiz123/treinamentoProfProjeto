package br.ufjf.dcc.progbotics.Tools;

import java.util.Date;

public class Registro {
    public Registro() {
    }

    public Registro(String cenario, String jogador) {
        this.cenario = cenario;
        this.jogador = jogador;
        this.inicio = new Date();
    }

    private String cenario;
    private String jogador;
    private Date inicio;
    private Date execucao;
    private Date conclusao;
    private String resultado;

    public String getCenario() {
        return cenario;
    }

    public void setCenario(String cenario) {
        this.cenario = cenario;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getExecucao() {
        return execucao;
    }

    public void setExecucao(Date execucao) {
        this.execucao = execucao;
    }

    public Date getConclusao() {
        return conclusao;
    }

    public void setConclusao(Date conclusao) {
        this.conclusao = conclusao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void iniciado(){
        this.inicio = new Date();
    }

    public void executado(){
        this.execucao = new Date();
    }

    public void finalizado(String status){
        this.resultado = status;
        this.conclusao = new Date();
    }

    @Override
    public String toString() {
        return "Registro{" +
                "cenario='" + cenario + '\'' +
                ", jogador='" + jogador + '\'' +
                ", inicio=" + inicio +
                ", execucao=" + execucao +
                ", conclusao=" + conclusao +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}

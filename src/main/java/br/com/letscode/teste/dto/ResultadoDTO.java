package br.com.letscode.teste.dto;

import java.util.HashMap;
import java.util.Map;

public class ResultadoDTO {
    private boolean resultado = false;
    private String mensagem;
    private Map<String, String> error = null;
    public boolean isResultado() { return resultado; }
    public ResultadoDTO setResultado(boolean resultado) {
        this.resultado = resultado;
        return this;
    }

    public String getMensagem() { return mensagem; }

    public ResultadoDTO setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public Map<String, String> getErrors() {
        return error;
    }

    public ResultadoDTO putError(String campo, String mensagem) {
        if (error == null) {
            error = new HashMap<>();
        }
        this.error.put(campo, mensagem);
        return this;
    }
}

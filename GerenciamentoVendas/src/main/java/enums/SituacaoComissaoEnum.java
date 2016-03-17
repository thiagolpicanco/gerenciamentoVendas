package enums;

import java.util.HashMap;
import java.util.Map;

public enum SituacaoComissaoEnum {

	PAGA(1, "Pagamento Efetuado"), A_PAGAR(2, "Pagamento Pendente");

	private Integer codigoSituacao;
	private String descricao;

	static Map<Integer, SituacaoComissaoEnum> map = new HashMap<Integer, SituacaoComissaoEnum>();

	static {
		for (SituacaoComissaoEnum situacao : SituacaoComissaoEnum.values()) {
			map.put(situacao.getCodigoSituacao(), situacao);
		}
	}

	public SituacaoComissaoEnum getByCod(Integer cod) {
		return map.get(cod);

	}

	private SituacaoComissaoEnum(Integer codigoSituacao, String descricao) {
		this.codigoSituacao = codigoSituacao;
		this.descricao = descricao;
	}

	public Integer getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Integer codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

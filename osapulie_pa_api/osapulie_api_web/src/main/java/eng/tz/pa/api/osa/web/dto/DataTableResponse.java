package eng.tz.pa.api.osa.web.dto;
import java.util.List;

/**
 * Dalla documentazione di datatable https://datatables.net/manual/server-side
 * @param <T>
 */
public class DataTableResponse<T> extends BaseResponse<T>
{

	private static final long serialVersionUID = 5545597196894008566L;
	private Long draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	
	public DataTableResponse(Long draw, BaseResponse<T> baseRespo)
	{
		this.setRecordsFiltered(baseRespo.getNumeroOggettiTotali());
		this.setRecordsTotal(baseRespo.getNumeroOggettiTotali());
		this.setDraw(draw);
		this.setEsito(baseRespo.getEsito());
		this.setDescrizione(baseRespo.getDescrizione());
		this.setNumeroOggettiRestituiti(baseRespo.getNumeroOggettiRestituiti());
		this.setNumeroOggettiTotali(baseRespo.getNumeroOggettiTotali());
		this.setPayload(baseRespo.getPayload());
	}
	public DataTableResponse()
	{
		super();
	}
	public DataTableResponse(int esitoOperazione, String descrizioneOperazione, long numeroOggettiTotali, long numeroOggettiRestituiti, List<T> payload)
	{
		super(esitoOperazione, descrizioneOperazione, numeroOggettiTotali, numeroOggettiRestituiti, payload);
	}
	public DataTableResponse(Long draw, Long recordsTotal, Long recordsFiltered)
	{
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
	}
	public Long getDraw()
	{
		return draw;
	}
	public void setDraw(Long draw)
	{
		this.draw = draw;
	}
	public Long getRecordsTotal()
	{
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal)
	{
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered()
	{
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered)
	{
		this.recordsFiltered = recordsFiltered;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((draw == null) ? 0 : draw.hashCode());
		result = prime * result + ((recordsFiltered == null) ? 0 : recordsFiltered.hashCode());
		result = prime * result + ((recordsTotal == null) ? 0 : recordsTotal.hashCode());
		return result;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTableResponse other = (DataTableResponse) obj;
		if (draw == null)
		{
			if (other.draw != null)
				return false;
		}
		else if (!draw.equals(other.draw))
			return false;
		if (recordsFiltered == null)
		{
			if (other.recordsFiltered != null)
				return false;
		}
		else if (!recordsFiltered.equals(other.recordsFiltered))
			return false;
		if (recordsTotal == null)
		{
			if (other.recordsTotal != null)
				return false;
		}
		else if (!recordsTotal.equals(other.recordsTotal))
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "DataTableResponse [draw=" + draw + ", recordsTotal=" + recordsTotal + ", recordsFiltered=" + recordsFiltered + ", getEsitoOperazione()=" + getEsito() + ", getDescrizioneOperazione()=" + getDescrizione() + ", getNumeroOggettiTotali()=" + getNumeroOggettiTotali() + ", getNumeroOggettiRestituiti()=" + getNumeroOggettiRestituiti() + ", getPayload()=" + getPayload() + "]";
	}
}
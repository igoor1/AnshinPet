package fatec.sp.gov.br.anshinpet.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

}

package guess.lucky.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.java.DateTypeDescriptor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {
    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String subtitle;
    @ApiModelProperty(required = true)
    private String content;
    @ApiModelProperty(required = true)
    private String imgUrl;
    @ApiModelProperty(value = "Pattern : YYYY/MM/DD", required = true)
    @JsonFormat(pattern = DateTypeDescriptor.DATE_FORMAT)
    private Date expiredDate;
}

package model;

import javax.servlet.http.Part;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Csv {

    private String Title;
    private Part File;
}

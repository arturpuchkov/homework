package DTO;

import lombok.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreOut{
	private int petId;
	private int quantity;
	private int id;
	private String shipDate;
	private boolean complete;
	private String status;
}
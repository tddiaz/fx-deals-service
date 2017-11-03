package com.github.tddiaz.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by tristandiaz on 11/4/17.
 */
@Getter
@Setter
public class SummaryDto {

    private String validDeals;

    private String invalidDeals;

    private String datetime;

    private String processTime;

    private String filename;

}

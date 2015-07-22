/* Copyright (c) 2014 Pathways Community Network Institute
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.openhmis.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class ClientEnrollmentDevelopmentalDisabilityVO implements Serializable {
	private Long enrollmentId;

	// Program Specific Data Standards: Physical Disability (2014, 4.5)
	private Date informationDate;
	private Integer typeCode;
	private Integer independenceCode;
	private Integer documentedCode;
	private Integer treatmentCode;

	public ClientEnrollmentDevelopmentalDisabilityVO() {
		super();
	}
	public ClientEnrollmentDevelopmentalDisabilityVO(Long enrollmentId) {
		super();
		this.enrollmentId = enrollmentId;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	@Override
	public String toString() {
		return "";
	}
}


package de.metas.vertical.healthcare_ch.forum_datenaustausch_ch.invoice_xversion.model.payload.body.law;

import lombok.Builder;
import lombok.Value;

import javax.annotation.Nullable;

import javax.xml.datatype.XMLGregorianCalendar;

/*
 * #%L
 * vertical-healthcare_ch.invoice_gateway.forum_datenaustausch_ch.invoice_commons
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@Value
@Builder
public class XmlVvg
{
	@Nullable
	String insuredId;

	@Nullable
	String caseId;

	@Nullable
	XMLGregorianCalendar caseDate;

	@Nullable
	String contractNumber;
}

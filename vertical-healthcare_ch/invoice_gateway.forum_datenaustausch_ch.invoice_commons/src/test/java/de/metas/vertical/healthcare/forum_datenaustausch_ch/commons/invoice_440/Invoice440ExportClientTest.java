package de.metas.vertical.healthcare.forum_datenaustausch_ch.commons.invoice_440;

import static java.math.BigDecimal.TEN;

import java.time.Instant;
import java.util.GregorianCalendar;

import org.junit.Test;

import de.metas.invoice_gateway.spi.model.BPartner;
import de.metas.invoice_gateway.spi.model.EAN;
import de.metas.invoice_gateway.spi.model.Invoice;
import de.metas.invoice_gateway.spi.model.Money;

/*
 * #%L
 * metasfresh-healthcare.invoice_gateway.forum_datenaustausch_ch.invoice_commons
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

public class Invoice440ExportClientTest
{

	@Test
	public void test()
	{


		final Invoice invoice = Invoice.builder()
				.biller(BPartner.builder().ean(EAN.of("1234567890123")).build())
				.recipient(BPartner.builder().ean(EAN.of("2234567890123")).build())
				.amount(Money.of(TEN, "CHF"))
				.invoiceTimestamp(Instant.now())
				.invoiceDate(new GregorianCalendar(2018, 10, 12))
				.documentNumber("123456789_123456789_123456789_123456789_") // too long; max length is 35; expect some exception
				.alreadyPaidAmount(Money.of(TEN, "CHF"))
				.build();

		// invoke the method under test
		// TODO
	}

}

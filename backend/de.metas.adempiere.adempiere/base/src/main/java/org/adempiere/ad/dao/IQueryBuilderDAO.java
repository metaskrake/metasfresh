package org.adempiere.ad.dao;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.DBException;
import org.compiere.model.IQuery;

import de.metas.util.ISingletonService;

public interface IQueryBuilderDAO extends ISingletonService
{
	<T> IQuery<T> create(IQueryBuilder<T> builder);

	/**
	 * Gets SQL WHERE clause of given query filter.
	 * 
	 * @param ctx
	 * @param filter query filter
	 * @param sqlParamsOut sql parameters (out); this parameter can be <code>null</code>, but in case the filters are providing some parameters then an exception will be thrown.
	 * @return sql where clause
	 * @throws DBException in case given filter has nonSQL parts
	 */
	<T> String getSql(Properties ctx, ICompositeQueryFilter<T> filter, List<Object> sqlParamsOut);
}

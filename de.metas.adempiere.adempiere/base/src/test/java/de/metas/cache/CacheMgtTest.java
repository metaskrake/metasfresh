package de.metas.cache;

import java.util.Set;

import org.adempiere.test.AdempiereTestHelper;
import org.adempiere.util.lang.impl.TableRecordReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import de.metas.cache.CCache;
import de.metas.cache.CacheInterface;
import de.metas.cache.CacheLabel;
import de.metas.cache.CacheMgt;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2017 metas GmbH
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

public class CacheMgtTest
{
	@Before
	public void init()
	{
		AdempiereTestHelper.get().init();
	}

	@Test
	public void resetByTableNameAndRecordId()
	{
		final CacheMgt cacheManager = CacheMgt.get();

		final AssertTableName assertTable1 = new AssertTableName("Table1");
		cacheManager.register(assertTable1);
		cacheManager.register(new CCache<>("Table2", 1));

		cacheManager.reset("Table2", 100);
		assertTable1.assertResetForRecordIdWasNotCalled();
		cacheManager.reset("Table1", 100);
		assertTable1.assertResetForRecordIdWasCalled();
	}

	@Test
	public void resetByTableNameAndRecordId_CCache_alwaysCalled()
	{
		final CacheMgt cacheManager = CacheMgt.get();

		final CCache_resetForRecordId_Mocked<Object, Object> cache = new CCache_resetForRecordId_Mocked<>("Table1");
		cacheManager.register(cache);
		cacheManager.register(new CCache<>("Table2", 1));

		cacheManager.reset("Table2", 100);
		cache.assertResetForRecordIdWasNotCalled();

		cacheManager.reset("Table1", 100);
		cache.assertResetForRecordIdWasCalled();
	}

	private static class AssertTableName implements CacheInterface
	{
		private final String tableName;
		private boolean resetForRecordIdWasCalled;

		private AssertTableName(final String tableName)
		{
			this.tableName = tableName;
		}

		@Override
		public long getCacheId()
		{
			return 1;
		}

		@Override
		public long resetForRecordId(final TableRecordReference recordRef)
		{
			Assert.assertEquals("resetForRecordId shall not be invoked for table name", this.tableName, tableName);

			resetForRecordIdWasCalled = true;
			return 1;
		}

		public void assertResetForRecordIdWasCalled()
		{
			Assert.assertTrue("resetForRecordIdWasCalled", resetForRecordIdWasCalled);
		}

		public void assertResetForRecordIdWasNotCalled()
		{
			Assert.assertFalse("resetForRecordIdWasCalled", resetForRecordIdWasCalled);
		}

		@Override
		public long reset()
		{
			return 1;
		}

		@Override
		public long size()
		{
			return 1;
		}

		@Override
		public String getCacheName()
		{
			return tableName;
		}

		@Override
		public Set<CacheLabel> getLabels()
		{
			return ImmutableSet.of(CacheLabel.ofTableName(tableName));
		}
	}

	private static final class CCache_resetForRecordId_Mocked<K, V> extends CCache<K, V>
	{

		private boolean resetForRecordIdWasCalled;

		public CCache_resetForRecordId_Mocked(final String name)
		{
			super(name, 1);
		}

		@Override
		public long resetForRecordId(final TableRecordReference recordRef)
		{
			final long count = super.resetForRecordId(recordRef);

			resetForRecordIdWasCalled = true;

			return count;
		}

		public void assertResetForRecordIdWasNotCalled()
		{
			Assert.assertFalse("resetForRecordIdWasCalled", resetForRecordIdWasCalled);
		}

		public void assertResetForRecordIdWasCalled()
		{
			Assert.assertTrue("resetForRecordIdWasCalled", resetForRecordIdWasCalled);
		}
	}
}
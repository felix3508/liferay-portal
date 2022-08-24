/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayButton from '@clayui/button';

import {PRMPageRoute} from '../../common/enums/prmPageRoute';
import liferayNavigate from '../../common/utils/liferayNavigate';
import Table from './components/Table';
import useGetMDFListColumns from './hooks/useGetMDFListColumns';
import useGetMDFRequestListItems from './hooks/useGetMDFRequestListItems';

interface MDFRequestListItem {
	activityPeriod?: string;
	id: string;
	totalCostOfExpense?: string;
	totalRequested?: string;
}

const MDFRequestList = () => {
	const {data: listItems} = useGetMDFRequestListItems();
	// eslint-disable-next-line no-console
	console.log(
		'🚀 ~ file: MDFRequestList.tsx ~ line 29 ~ MDFRequestList ~ listItems',
		listItems
	);

	const {data: listColumns} = useGetMDFListColumns();

	const newMdfRequest = () => {
		liferayNavigate(PRMPageRoute.CREATE_MDF_REQUEST);
	};

	return (
		<div className="border-0 pb-3 pt-5 px-6 sheet">
			<h1>MDF Requests</h1>

			<div className="bg-neutral-1 p-3 rounded">
				<ClayButton className="mr-1" displayType="secondary">
					Export MDF Report
				</ClayButton>

				<ClayButton onClick={newMdfRequest}>New Request</ClayButton>
			</div>

			{listItems && (
				<div className="mt-3">
					<Table<MDFRequestListItem>
						borderless
						columns={listColumns}
						responsive
						rows={listItems}
					/>
				</div>
			)}
		</div>
	);
};
export default MDFRequestList;

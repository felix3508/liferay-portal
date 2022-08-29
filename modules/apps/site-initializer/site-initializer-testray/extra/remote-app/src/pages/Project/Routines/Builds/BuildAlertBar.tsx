/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayAlert from '@clayui/alert';
import ClayButton from '@clayui/button';
import ClayLabel from '@clayui/label';
import {useNavigate} from 'react-router-dom';

import i18n from '../../../../i18n';
import {TestrayTask} from '../../../../services/rest';
import {SUBTASK_STATUS, SUB_TASK_STATUS} from '../../../../util/constants';

type BuildAlertBarProps = {
	testrayTask: TestrayTask;
};

const alertProperties = {
	[SUB_TASK_STATUS.IN_ANALYSIS]: {
		...SUBTASK_STATUS[SUB_TASK_STATUS.IN_ANALYSIS],
		displayType: 'warning',
		label: i18n.translate('in-analysis'),
		text: i18n.translate('this-build-is-currently-in-analysis'),
	},
	[SUB_TASK_STATUS.ABANDONED]: {
		...SUBTASK_STATUS[SUB_TASK_STATUS.ABANDONED],
		displayType: 'secondary',
		label: i18n.translate('abandoned'),
		text: i18n.translate('this-builds-task-has-been-abandoned'),
	},
	[SUB_TASK_STATUS.COMPLETE]: {
		...SUBTASK_STATUS[SUB_TASK_STATUS.COMPLETE],
		displayType: 'primary',
		label: i18n.translate('complete'),
		text: i18n.translate('this-build-has-been-analyzed'),
	},
};

const BuildAlertBar: React.FC<BuildAlertBarProps> = ({testrayTask}) => {
	const navigate = useNavigate();

	const alertProperty = (alertProperties as any)[testrayTask.dueStatus];

	if (!alertProperty) {
		return null;
	}

	return (
		<ClayAlert
			actions={
				<ClayButton
					displayType={alertProperty.displayType}
					onClick={() => navigate(`/testflow/${testrayTask.id}`)}
					outline
					small
				>
					{i18n.translate('view-task')}
				</ClayButton>
			}
			className="build-alert-bar w-100"
			displayType={alertProperty.displayType}
			title={
				((
					<>
						<ClayLabel displayType={alertProperty.displayType}>
							{alertProperty.label}
						</ClayLabel>

						{alertProperty.text}
					</>
				) as unknown) as string
			}
			variant="inline"
		/>
	);
};

export default BuildAlertBar;

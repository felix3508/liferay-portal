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

import {useQuery} from '@apollo/client';
import {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';

import {Avatar, AvatarGroup} from '../../components/Avatar';
import Code from '../../components/Code';
import Container from '../../components/Layout/Container';
import ListView from '../../components/ListView/ListView';
import Loading from '../../components/Loading';
import ProgressBar from '../../components/ProgressBar';
import StatusBadge from '../../components/StatusBadge';
import QATable from '../../components/Table/QATable';
import {CTypePagination} from '../../graphql/queries';
import {
	TestraySubTask,
	getTestraySubTasks,
} from '../../graphql/queries/testraySubTask';
import {
	TestrayTask,
	getTestrayTaskRest,
} from '../../graphql/queries/testrayTask';
import useHeader from '../../hooks/useHeader';
import i18n from '../../i18n';
import {SUBTASK_STATUS, TEST_STATUS_LABEL} from '../../util/constants';
import {routines, tasks} from '../../util/mock';

const TestFlowTasks: React.FC = () => {
	const {assigned} = routines[0];
	const [progressScore, setProgressScore] = useState({
		incomplete: 1,
		other: 0,
		self: 1,
	});

	const {testrayTaskId} = useParams();

	const {data, loading} = useQuery<{testrayTask: TestrayTask}>(
		getTestrayTaskRest,
		{
			variables: {testrayTaskId},
		}
	);

	const {data: dataTestraySubTasks} = useQuery<
		CTypePagination<'testraySubTasks', TestraySubTask>
	>(getTestraySubTasks);

	const testrayTask = data?.testrayTask;
	// eslint-disable-next-line react-hooks/exhaustive-deps
	const testraySubTasks =
		dataTestraySubTasks?.c?.testraySubTasks?.items || [];

	const {setHeading, setTabs} = useHeader();

	useEffect(() => {
		if (testrayTask) {
			setTimeout(() => {
				setHeading([
					{
						category: i18n.translate('tasks'),
						title: testrayTask.name,
					},
				]);
			});
		}

		setTabs([]);
	}, [setHeading, testrayTask, setTabs]);

	useEffect(() => {
		if (testraySubTasks?.length) {
			const progressVal = {
				incomplete: 0,
				other: 0,
				self: 0,
			};

			const getKey = (status: number) => {
				if ([0, 1].includes(status)) {
					return 'incomplete';
				}

				if (status === 2) {
					return 'other';
				}

				return 'self';
			};

			for (const testraySubTask of testraySubTasks) {
				const key = getKey(testraySubTask.dueStatus);

				progressVal[key] += testraySubTask.score;
			}

			setProgressScore(progressVal);
		}
	}, [testraySubTasks]);

	if (loading || !testrayTask) {
		return <Loading />;
	}

	return (
		<>
			<Container className="pb-6" title="Task Details">
				<div className="d-flex flex-wrap">
					<div className="col-4 col-lg-4 col-md-12 pb-5">
						<QATable
							items={[
								{
									title: i18n.translate('status'),
									value: (
										<StatusBadge type="failed">
											{
												TEST_STATUS_LABEL[
													testrayTask.dueStatus
												]
											}
										</StatusBadge>
									),
								},
								{
									title: i18n.translate('assigned-users'),
									value: (
										<AvatarGroup
											assignedUsers={assigned}
											groupSize={3}
										/>
									),
								},
								{
									title: i18n.translate('created'),
									value: '8 Hours ago',
								},
							]}
						/>
					</div>

					<div className="col-8 col-lg-8 col-md-12">
						<QATable
							items={[
								{
									title: i18n.translate('project-name'),
									value:
										testrayTask.testrayBuild?.testrayProject
											?.name,
								},
								{
									title: i18n.translate('routine-name'),
									value:
										testrayTask.testrayBuild?.testrayRoutine
											?.name,
								},
								{
									title: i18n.translate('build-name'),
									value: testrayTask.testrayBuild?.name,
								},
							]}
						/>

						<ProgressBar
							displayTotalCompleted={false}
							items={tasks[1]}
							legend
						/>
					</div>
				</div>
			</Container>

			<Container className="mt-3" title="Progress (Score)">
				<div className="my-4">
					<ProgressBar items={progressScore} legend />
				</div>
			</Container>

			<Container className="mt-3" title={i18n.translate('subtasks')}>
				<ListView
					query={getTestraySubTasks}
					tableProps={{
						columns: [
							{
								clickable: true,
								key: 'name',
								value: i18n.translate('name'),
							},
							{
								clickable: true,
								key: 'dueStatus',
								render: (status) => (
									<StatusBadge
										type={SUBTASK_STATUS[status]?.color}
									>
										{SUBTASK_STATUS[status]?.label}
									</StatusBadge>
								),

								value: i18n.translate('status'),
							},
							{
								clickable: true,
								key: 'score',
								value: i18n.translate('score'),
							},
							{
								clickable: true,
								key: 'tests',
								value: i18n.translate('tests'),
							},
							{
								clickable: true,
								key: 'error',
								render: (value) => <Code>{value}</Code>,
								size: 'xl',
								value: i18n.translate('errors'),
							},
							{
								clickable: true,
								key: 'assignee',
								render: (assignee: any) =>
									assignee && (
										<Avatar
											displayName
											name={assignee[0]?.name}
											url={assignee[0]?.url}
										/>
									),
								size: 'sm',
								value: i18n.translate('assignee'),
							},
						],
						navigateTo: () => '/testflow/subtasks',
					}}
					transformData={(data) => data?.c?.testraySubTasks}
				/>
			</Container>
		</>
	);
};

export default TestFlowTasks;

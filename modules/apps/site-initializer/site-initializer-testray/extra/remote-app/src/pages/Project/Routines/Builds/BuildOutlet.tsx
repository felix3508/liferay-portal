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
import ClayChart from '@clayui/charts';
import {useEffect, useRef} from 'react';
import {
	Outlet,
	useLocation,
	useOutletContext,
	useParams,
} from 'react-router-dom';

import Container from '../../../../components/Layout/Container';
import QATable from '../../../../components/Table/QATable';
import {
	CType,
	TestrayBuild,
	getTestrayBuild,
} from '../../../../graphql/queries';
import useHeader from '../../../../hooks/useHeader';
import i18n from '../../../../i18n';
import {DATA_COLORS} from '../../../../util/constants';
import {getDonutLegend} from '../../../../util/graph';
import {TotalTestCases, getRandomMaximumValue} from '../../../../util/mock';

type BuildOverviewProps = {
	testrayBuild: TestrayBuild;
};

const BuildOverview: React.FC<BuildOverviewProps> = ({testrayBuild}) => {
	const ref = useRef<any>();

	const total = TotalTestCases.map(([, totalCase]) => totalCase).reduce(
		(prevValue, currentValue) => Number(prevValue) + Number(currentValue)
	);

	return (
		<>
			<Container title={i18n.translate('details')}>
				<QATable
					items={[
						{
							title: i18n.translate('product-version'),
							value: '7.0.x',
						},
						{
							title: i18n.translate('description'),
							value: testrayBuild.description,
						},
						{
							title: i18n.translate('git-hash'),
							value:
								testrayBuild.gitHash ||
								'c33e85e8b067d805a45956c76ad053ca98ffcc8a',
						},
						{
							title: i18n.translate('create-date'),
							value: testrayBuild.dateCreated,
						},
						{
							title: i18n.translate('created-by'),
							value: 'John Doe',
						},
						{title: i18n.translate('all-issues-found'), value: '-'},
					]}
				/>

				<div className="d-flex mt-4">
					<dl>
						<dd>{i18n.sub('x-minutes', '0')}</dd>

						<dd className="small-heading">
							{i18n.translate('total-estimated-time')}
						</dd>
					</dl>

					<dl className="ml-3">
						<dd>{i18n.sub('x-minutes', '0')}</dd>

						<dd className="small-heading">
							{i18n.translate('total-estimated-time')}
						</dd>
					</dl>

					<dl className="ml-3">
						<dd>{i18n.sub('x-minutes', '0')}</dd>

						<dd className="small-heading">
							{i18n.sub('time-x-total-issues', '0')}
						</dd>
					</dl>
				</div>
			</Container>

			<Container className="mt-4" title="Total Test Cases">
				<div className="row">
					<div className="col-2">
						<ClayChart
							data={{
								colors: {
									'BLOCKED': DATA_COLORS['metrics.blocked'],
									'FAILED': DATA_COLORS['metrics.failed'],
									'INCOMPLETE':
										DATA_COLORS['metrics.incomplete'],
									'PASSED': DATA_COLORS['metrics.passed'],
									'TEST FIX': DATA_COLORS['metrics.test-fix'],
								},
								columns: TotalTestCases,
								type: 'donut',
							}}
							donut={{
								expand: false,
								label: {
									show: false,
								},
								legend: {
									show: false,
								},
								title: total.toString(),
								width: 15,
							}}
							legend={{show: false}}
							onafterinit={() => {
								getDonutLegend(ref.current, {
									data: TotalTestCases.map(([name]) => name),
									elementId: 'testrayTotalMetricsGraphLegend',
									total: total as number,
								});
							}}
							ref={ref}
							size={{
								height: 200,
							}}
						/>
					</div>

					<div className="col-2">
						<div id="testrayTotalMetricsGraphLegend" />
					</div>

					<div className="col-8">
						<ClayChart
							axis={{
								y: {
									label: {
										position: 'outer-middle',
										text: i18n
											.translate('tests')
											.toUpperCase(),
									},
								},
							}}
							bar={{
								width: {
									max: 30,
								},
							}}
							data={{
								colors: {
									'BLOCKED': DATA_COLORS['metrics.blocked'],
									'FAILED': DATA_COLORS['metrics.failed'],
									'INCOMPLETE':
										DATA_COLORS['metrics.incomplete'],
									'PASSED': DATA_COLORS['metrics.passed'],
									'TEST FIX': DATA_COLORS['metrics.test-fix'],
								},
								columns: [
									[
										'PASSED',
										...getRandomMaximumValue(20, 1000),
									],
									[
										'FAILED',
										...getRandomMaximumValue(20, 500),
									],
									[
										'BLOCKED',
										...getRandomMaximumValue(20, 100),
									],
									[
										'TEST FIX',
										...getRandomMaximumValue(20, 100),
									],
									[
										'INCOMPLETE',
										...getRandomMaximumValue(20, 100),
									],
								],
								groups: [
									[
										'PASSED',
										'FAILED',
										'BLOCKED',
										'TEST FIX',
										'INCOMPLETE',
									],
								],
								type: 'bar',
							}}
							legend={{
								inset: {
									anchor: 'top-right',
									step: 1,
									x: 10,
									y: -20,
								},
								position: 'inset',
							}}
							padding={{
								bottom: 5,
								top: 20,
							}}
						/>
					</div>
				</div>
			</Container>
		</>
	);
};

type BuildOutletProps = {
	ignorePath: string;
};

const BuildOutlet: React.FC<BuildOutletProps> = ({ignorePath}) => {
	const {pathname} = useLocation();
	const {projectId, routineId, testrayBuildId} = useParams();
	const {testrayProject, testrayRoutine}: any = useOutletContext();
	const {data} = useQuery<CType<'testrayBuild', TestrayBuild>>(
		getTestrayBuild,
		{
			variables: {
				testrayBuildId,
			},
		}
	);

	const isCurrentPathIgnored = pathname.includes(ignorePath);

	const testrayBuild = data?.c?.testrayBuild;

	const basePath = `/project/${projectId}/routines/${routineId}/build/${testrayBuildId}`;

	const {setHeading, setTabs} = useHeader({shouldUpdate: false});

	useEffect(() => {
		if (testrayBuild) {
			setTimeout(() => {
				setHeading(
					[
						{
							category: 'BUILD',
							path: basePath,
							title: testrayBuild.name,
						},
					],
					true
				);
			}, 0);
		}
	}, [basePath, setHeading, testrayBuild]);

	useEffect(() => {
		if (!isCurrentPathIgnored) {
			setTimeout(() => {
				setTabs([
					{
						active: pathname === basePath,
						path: basePath,
						title: i18n.translate('results'),
					},
					{
						active: pathname === `${basePath}/runs`,
						path: `${basePath}/runs`,
						title: i18n.translate('runs'),
					},
					{
						active: pathname === `${basePath}/teams`,
						path: `${basePath}/teams`,
						title: i18n.translate('teams'),
					},
					{
						active: pathname === `${basePath}/components`,
						path: `${basePath}/components`,
						title: i18n.translate('components'),
					},
					{
						active: pathname === `${basePath}/case-types`,
						path: `${basePath}/case-types`,
						title: i18n.translate('case-types'),
					},
				]);
			}, 5);
		}
	}, [basePath, isCurrentPathIgnored, pathname, setTabs]);

	if (testrayProject && testrayRoutine && testrayBuild) {
		return (
			<>
				{!isCurrentPathIgnored && (
					<BuildOverview testrayBuild={testrayBuild} />
				)}

				<Outlet />
			</>
		);
	}

	return null;
};

export default BuildOutlet;

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
import {useCallback, useContext, useEffect} from 'react';
import {Outlet, useLocation, useParams} from 'react-router-dom';

import {HeaderContext, HeaderTypes} from '../../context/HeaderContext';
import {CTypePagination} from '../../graphql/queries';
import {
	TestrayProject,
	TestrayProjectQuery,
	getTestrayProject,
	getTestrayProjects,
} from '../../graphql/queries/testrayProject';
import useHeader from '../../hooks/useHeader';
import i18n from '../../i18n';

const ProjectOutlet = () => {
	const {projectId, ...otherParams} = useParams();
	const {pathname} = useLocation();
	const {setHeading, setTabs} = useHeader();

	const [, dispatch] = useContext(HeaderContext);

	const {data} = useQuery<TestrayProjectQuery>(getTestrayProject, {
		variables: {testrayProjectId: projectId},
	});

	const {data: dataTestrayProjects} = useQuery<
		CTypePagination<'testrayProjects', TestrayProject>
	>(getTestrayProjects, {
		variables: {
			pageSize: 100,
		},
	});

	const testrayProjects = dataTestrayProjects?.c?.testrayProjects?.items;

	const hasOtherParams = !!Object.values(otherParams).length;
	const testrayProject = data?.c.testrayProject;

	const getPath = useCallback(
		(path: string) => {
			const relativePath = `/project/${projectId}/${path}`;

			return {
				active: relativePath === pathname,
				path: relativePath,
			};
		},
		[projectId, pathname]
	);

	useEffect(() => {
		if (testrayProjects) {
			dispatch({
				payload: [
					{
						items: [
							{
								divider: true,
								label: i18n.translate('project-directory'),
								path: '/',
							},
							...testrayProjects.map((testrayProject) => ({
								label: testrayProject.name,
								path: `/project/${testrayProject.testrayProjectId}/routines`,
							})),
						],
					},
				],
				type: HeaderTypes.SET_DROPDOWN,
			});
		}
	}, [dispatch, testrayProjects]);

	useEffect(() => {
		if (testrayProject && !hasOtherParams) {
			setHeading([
				{
					category: i18n.translate('project').toUpperCase(),
					title: testrayProject.name,
				},
			]);
		}
	}, [setHeading, testrayProject, hasOtherParams]);

	useEffect(() => {
		if (!hasOtherParams) {
			setTimeout(() => {
				setTabs([
					{
						...getPath('overview'),
						title: i18n.translate('overview'),
					},
					{
						...getPath('routines'),
						title: i18n.translate('routines'),
					},
					{
						...getPath('suites'),
						title: i18n.translate('suites'),
					},
					{
						...getPath('cases'),
						title: i18n.translate('cases'),
					},
					{
						...getPath('requirements'),
						title: i18n.translate('requirements'),
					},
				]);
			}, 0);
		}
	}, [getPath, setTabs, hasOtherParams]);

	if (testrayProject) {
		return <Outlet context={{testrayProject}} />;
	}

	return null;
};

export default ProjectOutlet;

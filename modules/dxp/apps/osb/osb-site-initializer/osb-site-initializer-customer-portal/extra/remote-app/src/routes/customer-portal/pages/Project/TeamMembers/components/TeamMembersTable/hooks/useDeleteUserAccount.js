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

import {useDeleteUserAccountByEmailAddress} from '../../../../../../../../common/services/liferay/graphql/user-accounts';
import {useDeleteRolesByContactEmailAddress} from '../../../../../../../../common/services/raysource/graphql/roles';

export default function useDeleteUserAccount() {
	const [
		deleteUserAccount,
		{called: deleteUserAccountCalled, loading: deleteUserAccountLoading},
	] = useDeleteUserAccountByEmailAddress();
	const [
		deleteContactRoles,
		{called: deleteContactRolesCalled, loading: deleteContactRolesLoading},
	] = useDeleteRolesByContactEmailAddress({
		onCompleted: (_, {variables}) =>
			deleteUserAccount({
				variables: {
					emailAddress: variables.contactEmail,
					externalReferenceCode: variables.externalReferenceCode,
				},
			}),
	});

	const deleting = deleteUserAccountLoading || deleteContactRolesLoading;
	const deleteCalled = deleteUserAccountCalled && deleteContactRolesCalled;

	return {
		deleteCalled,
		deleteContactRoles,
		deleting,
	};
}

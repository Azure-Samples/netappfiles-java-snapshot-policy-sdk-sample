// Copyright (c) Microsoft and contributors.  All rights reserved.
//
// This source code is licensed under the MIT license found in the
// LICENSE file in the root directory of this source tree.

package snapshotpolicy.sdk.sample;

import com.microsoft.azure.management.netapp.v2020_06_01.implementation.AzureNetAppFilesManagementClientImpl;
import com.microsoft.azure.management.netapp.v2020_06_01.implementation.CapacityPoolInner;
import com.microsoft.azure.management.netapp.v2020_06_01.implementation.NetAppAccountInner;
import com.microsoft.azure.management.netapp.v2020_06_01.implementation.SnapshotPolicyInner;
import com.microsoft.azure.management.netapp.v2020_06_01.implementation.VolumeInner;
import snapshotpolicy.sdk.sample.common.Utils;

import java.util.concurrent.CompletableFuture;

public class Creation
{
    /**
     * Creates an ANF Account
     * @param anfClient Azure NetApp Files Management Client
     * @param resourceGroup Name of the resource group where the Account will be created
     * @param accountName Name of the Account being created
     * @param accountBody The Account body used in the creation
     * @return The newly created ANF Account
     */
    public static CompletableFuture<NetAppAccountInner> createANFAccount(AzureNetAppFilesManagementClientImpl anfClient, String resourceGroup,
                                                                          String accountName, NetAppAccountInner accountBody)
    {
        NetAppAccountInner anfAccount = anfClient.accounts().createOrUpdate(resourceGroup, accountName, accountBody);
        Utils.writeSuccessMessage("Account successfully created, resourceId: " + anfAccount.id());

        return CompletableFuture.completedFuture(anfAccount);
    }

    /**
     * Creates a Snapshot Policy
     * @param anfClient Azure NetApp Files Management Client
     * @param resourceGroup Name of the resource group where the Snapshot Policy will be created
     * @param accountName Name of the Account
     * @param snapshotPolicyName Name of the Snapshot Policy being created
     * @param policyBody The Snapshot Policy body used in the creation
     * @return The newly create Snapshot Policy
     */
    public static CompletableFuture<SnapshotPolicyInner> createSnapshotPolicy(AzureNetAppFilesManagementClientImpl anfClient, String resourceGroup,
                                                                               String accountName, String snapshotPolicyName, SnapshotPolicyInner policyBody)
    {
        SnapshotPolicyInner snapshotPolicy = anfClient.snapshotPolicies().create(resourceGroup, accountName, snapshotPolicyName, policyBody);
        Utils.writeSuccessMessage("Snapshot Policy successfully created, resourceId: " + snapshotPolicy.id());

        return CompletableFuture.completedFuture(snapshotPolicy);
    }

    /**
     * Creates a Capacity Pool
     * @param anfClient Azure NetApp Files Management Client
     * @param resourceGroup Name of the resource group where the Capacity Pool will be created
     * @param accountName Name of the Account
     * @param poolName Name of the Capacity Pool being created
     * @param poolBody The Capacity Pool body used in the creation
     * @return The newly created Capacity Pool
     */
    public static CompletableFuture<CapacityPoolInner> createCapacityPool(AzureNetAppFilesManagementClientImpl anfClient, String resourceGroup,
                                                                           String accountName, String poolName, CapacityPoolInner poolBody)
    {
        CapacityPoolInner capacityPool = anfClient.pools().createOrUpdate(resourceGroup, accountName, poolName, poolBody);
        Utils.writeSuccessMessage("Capacity Pool successfully created, resourceId: " + capacityPool.id());

        return CompletableFuture.completedFuture(capacityPool);
    }

    /**
     * Creates a Volume with an attached Snapshot Policy
     * @param anfClient Azure NetApp Files Management Client
     * @param resourceGroup Name of the resource group where the Volume will be created
     * @param accountName Name of the Account
     * @param poolName Name of the Capacity Pool
     * @param volumeName Name of the Volume being created
     * @param volumeBody The Volume body used in the creation
     * @return The newly created Volume
     */
    public static CompletableFuture<VolumeInner> createVolume(AzureNetAppFilesManagementClientImpl anfClient, String resourceGroup,
                                                               String accountName, String poolName, String volumeName, VolumeInner volumeBody)
    {
        VolumeInner volume = anfClient.volumes().createOrUpdate(resourceGroup, accountName, poolName, volumeName, volumeBody);
        Utils.writeSuccessMessage("Volume successfully created, resourceId: " + volume.id());

        return CompletableFuture.completedFuture(volume);
    }
}

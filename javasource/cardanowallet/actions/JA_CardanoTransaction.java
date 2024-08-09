// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package cardanowallet.actions;

import com.bloxbean.cardano.client.account.Account;
import com.bloxbean.cardano.client.api.model.Result;
import com.bloxbean.cardano.client.backend.api.DefaultProtocolParamsSupplier;
import com.bloxbean.cardano.client.backend.api.DefaultUtxoSupplier;
import com.bloxbean.cardano.client.backend.blockfrost.common.Constants;
import com.bloxbean.cardano.client.backend.blockfrost.service.BFBackendService;
import com.bloxbean.cardano.client.cip.cip20.MessageMetadata;
import com.bloxbean.cardano.client.common.model.Network;
import com.bloxbean.cardano.client.common.model.Networks;
import com.bloxbean.cardano.client.function.Output;
import com.bloxbean.cardano.client.function.TxBuilder;
import com.bloxbean.cardano.client.function.TxBuilderContext;
import com.bloxbean.cardano.client.function.helper.AuxDataProviders;
import com.bloxbean.cardano.client.function.helper.BalanceTxBuilders;
import com.bloxbean.cardano.client.function.helper.InputBuilders;
import com.bloxbean.cardano.client.function.helper.SignerProviders;
import com.bloxbean.cardano.client.transaction.spec.Transaction;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import static com.bloxbean.cardano.client.common.CardanoConstants.LOVELACE;
import static com.bloxbean.cardano.client.common.ADAConversionUtil.adaToLovelace;
import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import cardanowallet.EncryptDecryptMnemonic;

public class JA_CardanoTransaction extends CustomJavaAction<java.lang.String>
{
	private java.lang.String ReceiverAddress;
	private java.math.BigDecimal Amount;
	private cardanowallet.proxies.Enum_CardanoNetwork CardanoNetwork;
	private java.lang.String EncryptedMnemonic;
	private java.lang.String Passphrase;
	private java.lang.String TransactionMetaData;

	public JA_CardanoTransaction(IContext context, java.lang.String ReceiverAddress, java.math.BigDecimal Amount, java.lang.String CardanoNetwork, java.lang.String EncryptedMnemonic, java.lang.String Passphrase, java.lang.String TransactionMetaData)
	{
		super(context);
		this.ReceiverAddress = ReceiverAddress;
		this.Amount = Amount;
		this.CardanoNetwork = CardanoNetwork == null ? null : cardanowallet.proxies.Enum_CardanoNetwork.valueOf(CardanoNetwork);
		this.EncryptedMnemonic = EncryptedMnemonic;
		this.Passphrase = Passphrase;
		this.TransactionMetaData = TransactionMetaData;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		Network selectedNetwork;
		String blockfrostUrl;

		String networkString = CardanoNetwork.name();
		if(networkString.equalsIgnoreCase("preprod")) {
			selectedNetwork = Networks.preprod();
			blockfrostUrl = Constants.BLOCKFROST_PREPROD_URL;
		} else if(networkString.equalsIgnoreCase("testnet")) {
			selectedNetwork = Networks.testnet();
			blockfrostUrl = Constants.BLOCKFROST_TESTNET_URL;
		} else if(networkString.equalsIgnoreCase("preview")) {
			blockfrostUrl = Constants.BLOCKFROST_PREVIEW_URL;
			selectedNetwork = Networks.preview();
		} else {
			selectedNetwork = Networks.mainnet();
			blockfrostUrl = Constants.BLOCKFROST_MAINNET_URL;
		}

		// Decrypt the mnemonic
		EncryptDecryptMnemonic decryptMnemonic = new EncryptDecryptMnemonic();
		String mnemonic = decryptMnemonic.decrypt(this.EncryptedMnemonic, this.Passphrase);

		Account senderAccount = new Account(selectedNetwork, mnemonic);
		LOG.info(senderAccount);
		String senderAddress = senderAccount.baseAddress();
		
		String receiverAddress1 = this.ReceiverAddress;

		String bfProjectId = cardanowallet.proxies.constants.Constants.getBLOCKFROST_PROJECTID();
		BFBackendService backendService =
		        new BFBackendService(blockfrostUrl, bfProjectId);
		
		Output output1 = Output.builder()
                .address(receiverAddress1)
                .assetName(LOVELACE)
                .qty(adaToLovelace(this.Amount.doubleValue()))
                .build();
		// make this dynamic
		MessageMetadata metadata = MessageMetadata.create()
                .add(this.TransactionMetaData);
		
		TxBuilder txBuilder = output1.outputBuilder()
                .buildInputs(InputBuilders.createFromSender(senderAddress, senderAddress))
                .andThen(AuxDataProviders.metadataProvider(metadata))
                .andThen(BalanceTxBuilders.balanceTx(senderAddress, 1));
		
		DefaultUtxoSupplier utxoSupplier = new DefaultUtxoSupplier(backendService.getUtxoService());
		DefaultProtocolParamsSupplier protocolParamsSupplier = new DefaultProtocolParamsSupplier(backendService.getEpochService());

		Transaction signedTransaction = TxBuilderContext.init(utxoSupplier, protocolParamsSupplier)
		                                    .buildAndSign(txBuilder, SignerProviders.signerFrom(senderAccount));
		
		Result<String> result = backendService.getTransactionService().submitTransaction(signedTransaction.serialize());
		LOG.info(result);
		return result.getValue();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "JA_CardanoTransaction";
	}

	// BEGIN EXTRA CODE
	public static ILogNode LOG = Core.getLogger("LandanoTest");
	/*public void waitForTransaction(Result<String> result) {
		try {
			if (result.isSuccessful()) { //Wait for transaction to be mined
				int count = 0;
				while (count < 60) {
					Result<TransactionContent> txnResult = transactionService.getTransaction(result.getValue());
					if (txnResult.isSuccessful()) {
						System.out.println(JsonUtil.getPrettyJson(txnResult.getValue()));
						break;
					} else {
						System.out.println("Waiting for transaction to be mined ....");
					}
					count++;
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	// END EXTRA CODE
}

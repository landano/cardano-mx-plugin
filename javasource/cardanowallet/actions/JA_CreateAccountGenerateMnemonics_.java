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
import com.bloxbean.cardano.client.common.model.Network;
import com.bloxbean.cardano.client.common.model.Networks;
import com.bloxbean.cardano.client.function.Output;
import com.bloxbean.cardano.client.function.TxBuilder;
import com.bloxbean.cardano.client.function.TxBuilderContext;
import com.bloxbean.cardano.client.backend.api.DefaultProtocolParamsSupplier;
import com.bloxbean.cardano.client.backend.api.DefaultUtxoSupplier;
import com.bloxbean.cardano.client.backend.blockfrost.common.Constants;
import com.bloxbean.cardano.client.backend.blockfrost.service.BFBackendService;
import com.bloxbean.cardano.client.cip.cip20.MessageMetadata;
import com.bloxbean.cardano.client.function.helper.AuxDataProviders;
import com.bloxbean.cardano.client.function.helper.BalanceTxBuilders;
import com.bloxbean.cardano.client.function.helper.InputBuilders;
import com.bloxbean.cardano.client.function.helper.SignerProviders;
import com.bloxbean.cardano.client.transaction.spec.Transaction;
import static com.bloxbean.cardano.client.common.CardanoConstants.LOVELACE;
import com.bloxbean.cardano.client.quicktx.*;
import java.util.ArrayList;
import java.util.List;
import static com.bloxbean.cardano.client.common.ADAConversionUtil.adaToLovelace;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import cardanowallet.proxies.Mnemonic_word;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class JA_CreateAccountGenerateMnemonics_ extends CustomJavaAction<IMendixObject>
{
	private IMendixObject __Wallet;
	private cardanowallet.proxies.WalletAPI Wallet;
	private java.lang.String CARDANONETWORK;

	public JA_CreateAccountGenerateMnemonics_(IContext context, IMendixObject Wallet, java.lang.String CARDANONETWORK)
	{
		super(context);
		this.__Wallet = Wallet;
		this.CARDANONETWORK = CARDANONETWORK;
	}

	@java.lang.Override
	public IMendixObject executeAction() throws Exception
	{
		this.Wallet = this.__Wallet == null ? null : cardanowallet.proxies.WalletAPI.initialize(getContext(), __Wallet);

		// BEGIN USER CODE
		//create a wallet -> create accounts and attach them to this wallet-> generate mnenomics (recovery phrase)
		// confirm that I confirm that nobody can see my screen, because anyone who knows my recovery phrase will be able to spend the funds in my wallet.
		// show the list of mnenomics
		// capture the list of mnenomics (confirm the recovery phrase)
		/*
			List<String> mnenomicList = new ArrayList<String>();
			List<IMendixObject> mnenomicWordMxObjects = new ArrayList<IMendixObject>();
			Account account = new Account(Net);
			String mnemonicSentence = account.mnemonic();
			String[] mnenomicArray = mnemonicSentence.split(" ");
			IContext context = getContext();
			for(String mnemonicWord : mnenomicArray) {
				Mnemonic_word mnemonicWordMx = new Mnemonic_word(context);
				mnemonicWordMx.setValue(mnemonicWord);
				mnenomicWordMxObjects.add((IMendixObject) mnemonicWordMx);
			}

			return mnenomicWordMxObjects;
		*/
		IContext context = getContext();
		Network selectedNetwork;
		String networkString = this.Wallet.getCardanoNetwork().name();
		networkString =  networkString.length() > 0 ? networkString: "mainnet";
		if(networkString.equalsIgnoreCase("preprod")) {
			selectedNetwork = Networks.preprod();
		} else if(networkString.equalsIgnoreCase("testnet")) {
			selectedNetwork = Networks.testnet();
		} else if(networkString.equalsIgnoreCase("preview")) {
			selectedNetwork = Networks.preview();
		} else {
			selectedNetwork = Networks.mainnet();
		}
		Account newAccount = new Account(selectedNetwork);
		String mnemonicSentence = newAccount.mnemonic();
		this.Wallet.setMnemonic(mnemonicSentence);
		this.Wallet.setBaseAddress(newAccount.baseAddress());
		this.Wallet.setStakeAddress(newAccount.stakeAddress());

		// create a sentence object
		// create list of mnemonic words for us on UI
		// create object
		IMendixObject newObject = Core.instantiate(context, "CardanoWallet.Mnemonic_sentence");
		cardanowallet.proxies.Mnemonic_sentence mnemonicSentenceMx = cardanowallet.proxies.Mnemonic_sentence.initialize(context, newObject);
		mnemonicSentenceMx.setCompleteSentence(mnemonicSentence);
		mnemonicSentenceMx.setMnemonic_sentence_WalletAPI(this.Wallet);
		this.Wallet.setMnemonic_sentence_WalletAPI(mnemonicSentenceMx);

		
		String[] mnenomicArray = mnemonicSentence.split(" ");

		Integer index = 0;
		for(String mnemonicWord : mnenomicArray) {
			IMendixObject Mnemonic_wordMx = Core.instantiate(context, "CardanoWallet.Mnemonic_word");
			cardanowallet.proxies.Mnemonic_word mnemonicWordMx = cardanowallet.proxies.Mnemonic_word.initialize(context, Mnemonic_wordMx);
			index++;
			mnemonicWordMx.setOrder(index);
			mnemonicWordMx.setValue(mnemonicWord);
			mnemonicWordMx.setMnemonic_word_Mnemonic_sentence(mnemonicSentenceMx);
			mnemonicWordMx.setMnemonic_word_WalletAPI(this.Wallet);
		}

		return this.Wallet.getMendixObject();
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "JA_CreateAccountGenerateMnemonics_";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}

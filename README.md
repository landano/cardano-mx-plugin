# Cardano Mendix Plugin by Landano

## Overview

The **Cardano Mendix Plugin** is a powerful bridge between the Mendix low-code platform and the Cardano blockchain. Developed by [Landano](https://landano.io), this plugin empowers Mendix developers to build decentralized applications (dApps) that support Cardano-native features like wallet creation, ADA transactions, NFT minting, metadata attachment, and smart contract interaction — all without leaving the Mendix ecosystem.

The plugin is fully open-source and packaged as a set of Mendix modules with Java and JavaScript actions. It supports both custodial and browser-based wallets and uses [Blockfrost](https://blockfrost.io) as its backend.

---

## Features

### ✅ Core Capabilities
- 🔐 Wallet creation and restoration (with secure mnemonic encryption)
- 💸 Send ADA with or without metadata
- 🔄 Multi-signature transactions (native scripts)
- 🧠 Smart contract support (lock/unlock flows)
- 🖼️ Mint and burn native NFTs with optional IPFS metadata (via Pinata)
- 🔗 Browser wallet connection (CIP-30: Nami, Eternl, etc.)

### 📦 Included Modules
- `CardanoWallet`: Core plugin logic and blockchain flows
- `Blockfrost`: API integration for submitting and querying the blockchain
- `PinataIPFS`: Optional module to upload media to IPFS for NFT metadata

---

## Prerequisites

To get started, you’ll need:

- Mendix Studio Pro 10.x
- Java 11+
- A [Blockfrost](https://blockfrost.io) API key (for testnet or mainnet)
- Optional: A [Pinata](https://pinata.cloud) API key if you're using IPFS for NFTs

⚠️ The plugin **does not currently support connecting to your own Cardano node** — Blockfrost is required for all blockchain operations.

---

## Installation

1. **Clone the Repository:**

```bash
git clone https://github.com/landano/cardano-mendix-plugin.git
```

2. **Import into Mendix:**
   - Open your Mendix project in Studio Pro.
   - Import the `.mpk` modules in this order:
     1. `CardanoWallet`
     2. `Blockfrost`
     3. `PinataIPFS` *(optional)*

3. **Configure Blockfrost API:**
   - Add your API key to the appropriate constant or configuration entity.
   - Choose the correct network (e.g. `Preprod`, `Mainnet`).

---

## Usage

The fastest way to explore the plugin is to open the **example project** included in this repository. It includes a fully functional UI and prebuilt flows.

### Wallet Management

You can:
- **Create a new wallet** with a secure passphrase and generated mnemonic
- **Restore an existing wallet** using a 15-word phrase

All mnemonics are encrypted in the Mendix database using a passphrase + key setup.

### Transaction Workflow

The transaction process is broken into 4 user-friendly steps:

1. **Choose type:**
   - Simple ADA transaction
   - Metadata transaction
   - NFT minting
   - Multi-sig
   - Smart contract lock/unlock

2. **Enter transaction details**

3. **Verify the transaction**

4. **Confirm and submit with passphrase**

### NFT Minting

The plugin supports:
- One-click NFT minting with a metadata form
- Optional IPFS image or metadata pinning using the `PinataIPFS` module

### Browser Wallets (Web Only)

You can connect to wallets like **Lace** or **Eternl** in the browser using:
- `JS_Wallet_Connect`
- `JS_GetUsedCardanoWalletAddresses`
- `JS_GetCardanoWalletRewardAddresses`

---

## Documentation

You can find full documentation, guides, and developer references at:

👉 [https://docs.landano.io/d/cardano-mendix-plugin](https://docs.landano.io/d/cardano-mendix-plugin)

---

## Contributing

We welcome contributions from the Mendix and Cardano communities!

1. Fork the repository
2. Create a feature branch
3. Submit a pull request with a clear description

For larger changes, please open an issue first to discuss your idea.

---

## License

MIT License — see the [LICENSE](LICENSE) file.

---

## Contact

- Website: [https://www.landano.io](https://www.landano.io)
- GitHub Issues: [https://github.com/landano/cardano-mendix-plugin/issues](https://github.com/landano/cardano-mendix-plugin/issues)
- Email: support@landano.io

## Acknowledgements
The Landano team thanks the [Bloxbean](https://www.bloxbean.com/) team for their support. Without the [cardano-client-lib](https://github.com/bloxbean/cardano-client-lib) we wouldn't have been able to build the Cardano Mendix Plugin.

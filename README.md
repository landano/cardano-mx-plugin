# Cardano Mendix Plugin by Landano

## Overview

The **Cardano Mendix Plugin** integrates the Cardano blockchain with the Mendix low-code platform, enabling developers to build decentralized applications (dApps) on the Cardano network directly within Mendix. This plugin provides a seamless way to interact with Cardano smart contracts, manage transactions, and handle blockchain data.

## Features

### Initial Prototype
- **Secure Integration:** Leverage the security and scalability of the Cardano blockchain in your Mendix applications.
- **Wallet creation:** Create 
- **Transaction Management:** Create, sign, and submit transactions on the Cardano network.
- **Blockchain Data Access:** Retrieve information from the Cardano blockchain, such as block data, transaction history, and more.
- **Adding Metadata to Transactions:** Attach metadata to transactions to include additional information that can be stored and retrieved from the blockchain.

### Roadmap for September
- **Support for Multi-Sig Transactions:** Create and manage multi-signature transactions, ensuring that multiple parties authorize a transaction before it is processed.
- **Creation of Native Tokens:** Mint and manage native tokens on the Cardano blockchain, enabling custom assets within your Mendix applications.
- **Smart Contract Interaction:** Execute and interact with smart contracts on the Cardano blockchain.

## Prerequisites

Before you can use the Cardano Mendix Plugin, ensure that you have the following:

- Mendix Studio Pro installed.
- A Mendix project where the plugin will be integrated.
- Access to a Cardano node or a Cardano API service (e.g., Blockfrost).

## Installation

1. **Download the Plugin:**
   - Clone or download the repository from GitHub.

```bash
git clone https://github.com/yourusername/Cardano-Mendix-Plugin.git
```

2. **Add to Mendix Project:**
   - Open your Mendix project in Mendix Studio Pro.
   - Import the downloaded plugin into your project.

3. **Configure Plugin:**
   - Navigate to the plugin settings in Mendix and configure the Cardano node or API endpoint.
   - Set up necessary credentials and access tokens.

## Usage

### Managing Transactions

1. **Create a Transaction:**
   - Utilize the plugin to create a transaction object with the necessary parameters.

2. **Sign the Transaction:**
   - Use your private key (stored securely) to sign the transaction within the Mendix environment.

3. **Submit the Transaction:**
   - Submit the signed transaction to the Cardano network and handle any returned transaction IDs or errors.

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch-name`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch-name`).
5. Create a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions, feedback, or support, please open an issue in the repository or contact us using the form on our website at https://www.landano.io/contact

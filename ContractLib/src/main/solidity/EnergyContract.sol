// SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.7.0;

contract EnergyContract {
    /* define variable greeting of the type string */
    string greet;

    int amountToBeSold = 0;
    int amountToBeBought = 0;

    mapping(address => int) private sellers;

    uint256 head = 0;
    uint256 tail = 0;
    address[50] orderedSellers;

    event AnnounceSaleIntention(address seller);

    function sellEnergy(int amount) public returns (bool) {
        require(amount > 0, "Amount must be larger than 0");
        amountToBeSold += amount;

        if (sellers[msg.sender] > 0)
            return false;

        orderedSellers[tail++] = msg.sender;
        sellers[msg.sender] = amount;

        emit AnnounceSaleIntention(msg.sender);
        return true;
    }

    // DO NOT change parameter names or types
    // IF YOU MUST please modify the task fixPayableBug in gradlew.build
    // Its inconvenient but necessary sorry
    function buyEnergy(int buyAmount) public payable {
        amountToBeBought += buyAmount;

    }

    function getToBeSold() public view returns (int) {
        return amountToBeSold;
    }

}

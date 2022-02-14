// SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.7.0;

contract EnergyContract {
    /* define variable greeting of the type string */
    string greet;

    int amountToBeSold = 0;
    int amountToBeBought = 0;

    event AnnounceSaleIntention();

    function sellEnergy(int amount) public {
        amountToBeSold += amount;

        emit AnnounceSaleIntention();
    }

    function buyEnergy(int amount) public {
        amountToBeBought += amount;
    }

    function getToBeSold() public view returns (int) {
        return amountToBeSold;
    }

    event Modified(
        string indexed oldGreetingIdx, string indexed newGreetingIdx,
        string oldGreeting, string newGreeting);
}

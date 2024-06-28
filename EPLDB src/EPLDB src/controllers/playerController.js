const asyncHandler = require('express-async-handler');
const Player = require('../models/playerModel');

const getAllPlayers = asyncHandler(async (req, res) => {
    const players = await Player.find();
    res.status(200).json(players);
});

const createPlayer = asyncHandler(async (req, res) => {
    const { name, team, number, age, position, photoUrl } = req.body;
    const player = await Player.create({ name, team, number, age, position, photoUrl });
    res.status(201).json(player);
});

const getPlayer = asyncHandler(async (req, res) => {
    const player = await Player.findById(req.params.id);
    if (!player) {
        res.status(404);
        throw new Error('Player not found');
    }
    res.status(200).json(player);
});

const updatePlayer = asyncHandler(async (req, res) => {
    const { name,team, number, age, position, photoUrl } = req.body;
    const player = await Player.findById(req.params.id);
    if (!player) {
        res.status(404);
        throw new Error('Player not found');
    }
    player.name = name;
    player.team = team;
    player.number = number;
    player.age = age;
    player.position = position;
    player.photoUrl = photoUrl;
    const updatedPlayer = await player.save();
    res.status(200).json(updatedPlayer);
});

const deletePlayer = asyncHandler(async (req, res) => {
    const player = await Player.findById(req.params.id);
    if (!player) {
        res.status(404);
        throw new Error('Player not found');
    }
    await player.remove();
    res.status(200).json({ message: 'Player removed' });
});

module.exports = { getAllPlayers, createPlayer, getPlayer, updatePlayer, deletePlayer };
const express = require('express');
const router = express.Router();
const Player = require('../models/playerModel');

router.get('/', async (req, res) => {
    try {
        const players = await Player.find();
        res.render('playerList', { players }); 
    } catch (error) {
        res.status(500).send('Internal Server Error');
    }
});

router.get('/create', (req, res) => {
    res.render('createPlayer'); 
});

router.post('/create', async (req, res) => {
    try {
        const { name,team,  number, age, position, photoUrl} = req.body;
        const player = new Player({ name,team, number, age, position, photoUrl });
        await player.save();
        res.redirect('/players');
    } catch (error) {
        res.status(500).send('Internal Server Error');
    }
});

router.get('/:id/edit', async (req, res) => {
    try {
        const player = await Player.findById(req.params.id);
        res.render('editPlayer', { player, playerId: req.params.id });
    } catch (error) {
        res.status(500).send('Internal Server Error');
    }
});

router.post('/:id/edit', async (req, res) => {
    try {
        const { name,team, number, age, position, photoUrl } = req.body;
        await Player.findByIdAndUpdate(req.params.id, { name,team,  number, age, position, photoUrl });
        res.redirect('/players');
    } catch (error) {
        res.status(500).send('Internal Server Error');
    }
});

router.get('/:id/delete', async (req, res) =>{
    try{
        res.send(req.params.id);
    }catch(error){
        res.status(500).send('Internal Server Error');
    }
}).delete('/:id/delete', async(req, res)=> {try{
    await Player.findByIdAndDelete(req.params.id);
    res.redirect('/players');
} catch (error) {
    res.status(500).send('Internal Server Error');
}
});

router.get('/:id', async (req, res) => {
    try {
        const player = await Player.findById(req.params.id);
        if (!player) {
            return res.status(404).send('Player not found');
        }
        const playerWithPhoto = {
            _id: player._id,
            name: player.name,
            team: player.team,
            number: player.number,
            age: player.age,
            position: player.position,
            photoUrl: player.photoUrl,
        };
        res.render('playerDetail', { player: playerWithPhoto });
    } catch (error) {
        res.status(500).send('Internal Server Error');
    }
});

module.exports = router;